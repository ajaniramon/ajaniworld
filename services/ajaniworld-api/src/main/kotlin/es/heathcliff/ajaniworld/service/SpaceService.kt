package es.heathcliff.ajaniworld.service

import es.heathcliff.ajaniworld.mapper.map
import es.heathcliff.ajaniworld.mapper.mapFrom
import es.heathcliff.ajaniworld.model.SpaceDto
import es.heathcliff.ajaniworld.repository.SpaceRepository
import org.springframework.stereotype.Service

@Service
class SpaceService(private var spaceRepository: SpaceRepository) {
    fun findAll() : List<SpaceDto> = spaceRepository.findAll().map(::map)

    fun save(spaceDto: SpaceDto){
        spaceRepository.save(mapFrom(spaceDto))
    }
}