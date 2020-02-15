package es.heathcliff.ajaniworld.service

import es.heathcliff.ajaniworld.mapper.map
import es.heathcliff.ajaniworld.mapper.mapFrom
import es.heathcliff.ajaniworld.model.StateDto
import es.heathcliff.ajaniworld.repository.StateRepository
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import java.util.*

@Service
class StateService(private var stateRepository: StateRepository) {
    fun findAll() : List<StateDto> = stateRepository.findAll().map(::map)

    fun findById(id: String) : StateDto = map(stateRepository.getOne(id))

    fun save(stateDto: StateDto){
        stateRepository.save(mapFrom(stateDto))
    }
}