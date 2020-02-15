package es.heathcliff.ajaniworld.mapper

import es.heathcliff.ajaniworld.domain.model.Role
import es.heathcliff.ajaniworld.model.RoleDto

fun map(role: Role) : RoleDto = RoleDto(role.id, role.name, role.description)
fun map(roleDto: RoleDto) : Role = Role(roleDto.id, roleDto.name, roleDto.description, mutableListOf())