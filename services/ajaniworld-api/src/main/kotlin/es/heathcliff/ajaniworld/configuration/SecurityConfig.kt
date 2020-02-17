package es.heathcliff.ajaniworld.configuration

import es.heathcliff.ajaniworld.jwt.JWTAuthenticationFilter
import es.heathcliff.ajaniworld.jwt.JWTFactory
import es.heathcliff.ajaniworld.jwt.JWTLoginFilter
import es.heathcliff.ajaniworld.security.AjaniAuthenticationManager
import es.heathcliff.ajaniworld.security.UserSecurityService
import es.heathcliff.ajaniworld.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.session.SessionManagementFilter
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfig(private var userService: UserService, private var userSecurityService: UserSecurityService,
                     private var jwtFactory: JWTFactory) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/login").permitAll()
                .and()
                .addFilterBefore(jwtLoginFilter(userService),
                        UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(JWTAuthenticationFilter(jwtFactory), UsernamePasswordAuthenticationFilter::class.java)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun jwtLoginFilter(userService: UserService) : JWTLoginFilter{
        return JWTLoginFilter("/login", userService, jwtFactory)
    }

    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**")
    }
}