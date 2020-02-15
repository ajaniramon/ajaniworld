package es.heathcliff.ajaniworld.repository

import es.heathcliff.ajaniworld.domain.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository : JpaRepository<Role, String> {
    fun findByName(name: String): Role?
}