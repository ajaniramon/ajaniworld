package es.heathcliff.ajaniworld.model

import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class StateDto(val id: String?,
                    @NotNull
                    @NotEmpty
                    val name: String,
                    @NotNull
                    val country: CountryDto)