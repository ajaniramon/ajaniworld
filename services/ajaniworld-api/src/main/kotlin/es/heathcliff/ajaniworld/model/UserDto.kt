package es.heathcliff.ajaniworld.model

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class UserDto(val id: String?,
                   @NotNull
                   @NotEmpty
                   var code: String?,
                   @NotNull
                   @NotEmpty
                   var name: String?,
                   @NotNull
                   @NotEmpty
                   var surnames: String?,
                   @NotNull
                   @NotEmpty
                   var email: String?,
                   @NotNull
                   @NotEmpty
                   var phone: String?,
                   @NotNull
                   @NotEmpty
                   var password: String?,
                   var avatar: ByteArray?,
                   var roles: List<RoleDto>?)