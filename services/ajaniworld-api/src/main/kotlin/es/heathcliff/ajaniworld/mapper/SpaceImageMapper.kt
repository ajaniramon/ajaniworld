package es.heathcliff.ajaniworld.mapper

import es.heathcliff.ajaniworld.domain.model.SpaceImage
import es.heathcliff.ajaniworld.model.SpaceImageDto

fun mapTo(spaceImage: SpaceImage) : SpaceImageDto = SpaceImageDto(spaceImage.id, spaceImage.image)