package es.heathcliff.ajaniworld.security

import es.heathcliff.ajaniworld.model.AccountCredentials
import es.heathcliff.ajaniworld.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class AjaniAuthenticationManager(private var userService: UserService) :
        AuthenticationManager {


    override fun authenticate(authentication: Authentication?): Authentication {
        val credentials : UsernamePasswordAuthenticationToken = authentication as UsernamePasswordAuthenticationToken

        val isLoginSuccessful : Boolean = userService.login(credentials.principal as String, credentials.credentials as String)

        if(isLoginSuccessful){
            return UsernamePasswordAuthenticationToken(userService.findByCode(credentials.principal as String), credentials.credentials as String)
        }else{
            throw BadCredentialsException("Bad credentials!")
        }
    }
}