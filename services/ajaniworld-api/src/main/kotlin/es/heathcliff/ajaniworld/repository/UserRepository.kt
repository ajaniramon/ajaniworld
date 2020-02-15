package es.heathcliff.ajaniworld.repository

import es.heathcliff.ajaniworld.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, String>{
    fun findByName(name: String) : User?
    fun findByCode(code: String) : User?
    fun existsByCode(code: String?) : Boolean
    fun existsByEmail(email: String?) : Boolean
}