package es.heathcliff.ajaniworld.mapper

import es.heathcliff.ajaniworld.domain.model.Space
import es.heathcliff.ajaniworld.model.SpaceDto

fun map(space: Space) : SpaceDto = SpaceDto(space.id,
        space.name,
        space.address,
        space.city,
        space.zipCode,
        map(space.state), space.starredImage?.let { mapTo(it) })

fun mapFrom(spaceDto: SpaceDto) : Space = Space(spaceDto.id,
        spaceDto.name,
        spaceDto.address,
        spaceDto.city,
        spaceDto.zipCode,
        mapFrom(spaceDto.state),
        mutableListOf(),
        null)