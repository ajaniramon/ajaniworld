package es.heathcliff.ajaniworld.repository

import es.heathcliff.ajaniworld.domain.model.Space
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SpaceRepository : JpaRepository<Space, String> {
}