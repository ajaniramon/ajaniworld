package es.heathcliff.ajaniworld.controller

import es.heathcliff.ajaniworld.constants.Constants
import es.heathcliff.ajaniworld.constants.EndpointMappings
import es.heathcliff.ajaniworld.constants.SecurityPolicies
import es.heathcliff.ajaniworld.model.SpaceDto
import es.heathcliff.ajaniworld.service.SpaceService
import io.honeycomb.beeline.tracing.Beeline
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(EndpointMappings.SPACES)
class SpaceController(private var spaceService: SpaceService,
                      private var beeline: Beeline) {

    @PreAuthorize(SecurityPolicies.ALLOW_REGISTRED_USER)
    @GetMapping
    fun findAll(): List<SpaceDto>{
        beeline.activeSpan.addField(Constants.LOG_INFO, "findAll hit")
        return spaceService.findAll()
    }

    @PostMapping
    fun save(@Valid space: SpaceDto){
        beeline.activeSpan.addField(Constants.LOG_INFO, "findAll hit")
        return spaceService.save(space)
    }
}