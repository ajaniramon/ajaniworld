package es.heathcliff.ajaniworld.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import es.heathcliff.ajaniworld.constants.Constants
import es.heathcliff.ajaniworld.domain.model.User
import es.heathcliff.ajaniworld.model.LoginResult
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.HashMap

@Service
class JWTFactory {
    private val expiration: Long = 100L
    private val header = "Authorization"
    private val objectMapper: ObjectMapper = ObjectMapper()

    @Value("\${ajaniworld.security.jwtSecret}")
    private lateinit var jwtSecret : String

    fun User.createJwt(): String {
        val claims = HashMap<String, Any>()

        // Add more claims if necessary
        claims.put("roles", this.roles.map { role -> role.name }.toList())
        claims.put("id", this.id)

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(this.code)
                .setExpiration(Date(Date().time + TimeUnit.HOURS.toMillis(expiration)))
                .signWith(SignatureAlgorithm.HS256, jwtSecret).compact()
    }

    fun addAuthentication(response: HttpServletResponse, user: User) {
        val jwt = user.createJwt()

        val loginResult = LoginResult(true, jwt,
                user.roles.any{role -> role.name.equals(Constants.ADMIN_ROLE_NAME)})

        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        response.setHeader("Access-Control-Allow-Origin", "*")

        response.writer.write(objectMapper.writeValueAsString(loginResult))
        response.writer.flush()
        response.writer.close()
    }

    fun addUnsuccessfulAuthentication(response: HttpServletResponse){
        val loginResult = LoginResult(false, null, false)

        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        response.setHeader("Access-Control-Allow-Origin", "*")

        response.setStatus(HttpStatus.OK.value())
        response.writer.write(objectMapper.writeValueAsString(loginResult))
        response.writer.flush()
        response.writer.close()
    }

    fun getAuthentication(request: HttpServletRequest): Authentication? {
        if(request.getHeader(header) == null){
            return null
        }else{
            val token = request.getHeader(header).replace("Bearer ", "")

            try{
                val tokenBody = Jwts.parser()
                        .setSigningKey(jwtSecret)
                        .parseClaimsJws(token)
                        .body

                val username: String = tokenBody.subject

                val roles = tokenBody["roles"] as List<*>

                val res = roles.mapTo(LinkedList<GrantedAuthority>()) { SimpleGrantedAuthority(it as String) }

                return UsernamePasswordAuthenticationToken(username, null, res)
            }catch (e: ExpiredJwtException){
                return null
            }
        }
    }
}