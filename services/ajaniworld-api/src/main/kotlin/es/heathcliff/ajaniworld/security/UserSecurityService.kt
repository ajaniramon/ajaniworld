package es.heathcliff.ajaniworld.security

import es.heathcliff.ajaniworld.domain.model.Role
import es.heathcliff.ajaniworld.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import kotlin.collections.forEach as forEach1

@Component
class UserSecurityService @Autowired
constructor(private val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(s: String): UserDetails {
        val user = userRepository.findByCode(s)
                ?: throw UsernameNotFoundException(String.format("The username %s doesn't exist", s))

        val authorities = ArrayList<GrantedAuthority>()
        user.roles.forEach1 { role : Role -> authorities.add(SimpleGrantedAuthority(role.name)) }

        return org.springframework.security.core.userdetails.User(user.name, user.password, authorities)
    }
}