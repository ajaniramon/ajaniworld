package es.heathcliff.ajaniworld.mapper

import es.heathcliff.ajaniworld.domain.model.State
import es.heathcliff.ajaniworld.model.StateDto

fun map(state: State) : StateDto = StateDto(state.id, state.name, map(state.country))
fun mapFrom(stateDto: StateDto) : State = State(stateDto.id, stateDto.name, mapFrom(stateDto.country))