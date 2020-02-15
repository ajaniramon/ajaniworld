package es.heathcliff.ajaniworld.repository

import es.heathcliff.ajaniworld.domain.model.Country
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CountryRepository : JpaRepository<Country, String> {
}