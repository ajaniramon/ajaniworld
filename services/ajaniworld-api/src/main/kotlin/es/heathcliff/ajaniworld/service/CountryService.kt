package es.heathcliff.ajaniworld.service

import es.heathcliff.ajaniworld.constants.Constants
import es.heathcliff.ajaniworld.exception.ColivingError
import es.heathcliff.ajaniworld.exception.ColivingException
import es.heathcliff.ajaniworld.mapper.map
import es.heathcliff.ajaniworld.mapper.mapFrom
import es.heathcliff.ajaniworld.model.CountryDto
import es.heathcliff.ajaniworld.repository.CountryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CountryService(private var countryRepository : CountryRepository) {
    fun findAll(): List<CountryDto> = countryRepository.findAll().map(::map)

    fun findById(id: String) : CountryDto = map(countryRepository.getOne(id))

    @Transactional
    fun save(countryDto: CountryDto){
        countryRepository.save(mapFrom(countryDto))
    }

    @Transactional
    fun deleteById(id: String){
        val country = countryRepository.findByIdOrNull(id)

        if(country == null){
            throw ColivingException(listOf(ColivingError(Constants.COUNTRY_FIELD_NAME, Constants.ERROR_NOT_EXISTS)))
        }

        if(!country.states.isEmpty()){
           throw ColivingException(listOf(ColivingError(Constants.COUNTRY_FIELD_NAME, Constants.ERROR_HAS_STATES)))
        }

        countryRepository.deleteById(id)
    }
}