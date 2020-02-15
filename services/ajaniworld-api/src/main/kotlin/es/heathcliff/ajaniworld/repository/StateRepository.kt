package es.heathcliff.ajaniworld.repository

import es.heathcliff.ajaniworld.domain.model.State
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface StateRepository : JpaRepository<State, String> {
}