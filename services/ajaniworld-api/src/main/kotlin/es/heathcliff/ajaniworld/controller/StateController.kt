package es.heathcliff.ajaniworld.controller

import es.heathcliff.ajaniworld.constants.Constants
import es.heathcliff.ajaniworld.constants.EndpointMappings
import es.heathcliff.ajaniworld.model.StateDto
import es.heathcliff.ajaniworld.service.StateService
import io.honeycomb.beeline.tracing.Beeline
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping(EndpointMappings.STATES)
class StateController(private var stateService: StateService,
                      private var beeline: Beeline) {

    @GetMapping
    fun findAll() : List<StateDto>{
        beeline.activeSpan.addField(Constants.LOG_INFO, "findAll hit")
        return stateService.findAll()
    }

    @GetMapping(EndpointMappings.ID_PARAM)
    fun findById(@PathVariable("id") id: String) : StateDto{
        beeline.activeSpan.addField(Constants.LOG_INFO, "findById hit")
        return stateService.findById(id)
    }

    @PostMapping
    fun save(@Valid @RequestBody stateDto: StateDto){
        beeline.activeSpan.addField(Constants.LOG_INFO, "save hit")
        return stateService.save(stateDto)
    }
}