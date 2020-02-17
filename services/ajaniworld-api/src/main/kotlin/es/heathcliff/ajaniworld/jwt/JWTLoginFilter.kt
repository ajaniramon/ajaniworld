package es.heathcliff.ajaniworld.jwt

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import es.heathcliff.ajaniworld.domain.model.User
import es.heathcliff.ajaniworld.model.AccountCredentials
import es.heathcliff.ajaniworld.security.AjaniAuthenticationManager
import es.heathcliff.ajaniworld.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsUtils
import java.io.IOException
import javax.naming.AuthenticationException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTLoginFilter internal constructor(private var s: String, private var userService: UserService,
                                          private var jwtFactory: JWTFactory)
    : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(s)) {
    init {
        authenticationManager = AjaniAuthenticationManager(userService)
    }

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(
            req: HttpServletRequest, res: HttpServletResponse): Authentication? {
        if(CorsUtils.isPreFlightRequest(req)){
            res.setStatus(HttpStatus.OK.value())
            res.setHeader("Access-Control-Allow-Origin", "*")
            res.setHeader("Access-Control-Allow-Headers", "*")
            return null;
        }

        if(!req.method.equals("POST")){
            res.setStatus(HttpStatus.NOT_FOUND.value())
            return null;
        }

        val cred: AccountCredentials? = jacksonObjectMapper().readValue(req.inputStream)

        return authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        cred?.username,
                        cred?.password,
                        emptyList<GrantedAuthority>()
                )
        )
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(req: HttpServletRequest,
                                          res: HttpServletResponse, chain: FilterChain?, auth: Authentication) {
        jwtFactory.addAuthentication(res, (auth.principal as User))
    }

    @Throws(IOException::class, ServletException::class)
    override fun unsuccessfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, failed: org.springframework.security.core.AuthenticationException?) {
        jwtFactory.addUnsuccessfulAuthentication(response)
    }
}