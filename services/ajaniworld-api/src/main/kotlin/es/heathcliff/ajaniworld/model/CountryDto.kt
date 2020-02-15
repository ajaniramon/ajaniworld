package es.heathcliff.ajaniworld.model

import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CountryDto(val id: String? = null,
                      @NotNull
                      @NotEmpty
                      val name: String?,
                      val flag: ByteArray?,
                      val hasStates: Boolean) {
}