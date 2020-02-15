package es.heathcliff.ajaniworld.model

import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class SpaceDto(val id: String?,
                    @NotNull
                    @NotEmpty
                    val name: String,
                    @NotNull
                    @NotEmpty
                    val address: String,
                    @NotNull
                    @NotEmpty
                    var city: String,
                    @NotNull
                    @NotEmpty
                    val zipCode: String,
                    val state: StateDto) {
}