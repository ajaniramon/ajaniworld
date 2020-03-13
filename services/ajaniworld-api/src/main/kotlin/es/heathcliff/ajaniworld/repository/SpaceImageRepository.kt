package es.heathcliff.ajaniworld.repository

import es.heathcliff.ajaniworld.domain.model.SpaceImage
import org.springframework.data.jpa.repository.JpaRepository

interface SpaceImageRepository : JpaRepository<SpaceImage, String>{
    fun existsBySpace_IdAndImage(spaceId: String, image: String) : Boolean
}