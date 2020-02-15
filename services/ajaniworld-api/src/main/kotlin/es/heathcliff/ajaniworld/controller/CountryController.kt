package es.heathcliff.ajaniworld.controller

import es.heathcliff.ajaniworld.constants.Constants
import es.heathcliff.ajaniworld.constants.EndpointMappings
import es.heathcliff.ajaniworld.constants.SecurityPolicies
import es.heathcliff.ajaniworld.model.CountryDto
import es.heathcliff.ajaniworld.service.CountryService
import io.honeycomb.beeline.tracing.Beeline
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(EndpointMappings.COUNTRIES)
class CountryController(private var countryService : CountryService,
                        private var beeline: Beeline) {

    @PreAuthorize(SecurityPolicies.ALLOW_REGISTRED_USER)
    @GetMapping
    fun findAll() : List<CountryDto>{
        beeline.activeSpan.addField(Constants.LOG_INFO, "findAll hit")
        return countryService.findAll()
    }

    @PreAuthorize(SecurityPolicies.ALLOW_ONLY_ADMIN)
    @PostMapping
    fun save(@Valid @RequestBody countryDto: CountryDto) {
        beeline.activeSpan.addField(Constants.LOG_INFO, "save hit")
        return countryService.save(countryDto)
    }

    @PreAuthorize(SecurityPolicies.ALLOW_ONLY_ADMIN)
    @DeleteMapping(EndpointMappings.ID_PARAM)
    fun deleteById(@PathVariable("id") id: String){
        countryService.deleteById(id)
    }
}