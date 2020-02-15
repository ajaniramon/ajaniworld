package es.heathcliff.ajaniworld.mapper

import es.heathcliff.ajaniworld.domain.model.User
import es.heathcliff.ajaniworld.model.UserDto

fun map(user: User) : UserDto = UserDto(user.id, user.code, user.name, user.surnames, user.email, user.phone, null, user.avatar, user.roles.map{role -> map(role)})
fun mapFrom(user: UserDto) = User(user.id, user.code, user.name, user.surnames, user.email, user.phone, "",user.avatar, mutableListOf())