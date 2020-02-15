package es.heathcliff.ajaniworld.mapper

import es.heathcliff.ajaniworld.domain.model.Country
import es.heathcliff.ajaniworld.domain.model.State
import es.heathcliff.ajaniworld.model.CountryDto

fun map(country: Country) : CountryDto = CountryDto(country.id, country.name, country.flag, !country.states.isEmpty())
fun mapFrom(countryDto: CountryDto) : Country = Country(countryDto.id, countryDto.name,countryDto.flag, emptySet())