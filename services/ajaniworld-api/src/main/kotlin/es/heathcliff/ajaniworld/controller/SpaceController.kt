package es.heathcliff.ajaniworld.controller

import es.heathcliff.ajaniworld.constants.Constants
import es.heathcliff.ajaniworld.constants.EndpointMappings
import es.heathcliff.ajaniworld.constants.SecurityPolicies
import es.heathcliff.ajaniworld.model.SpaceDto
import es.heathcliff.ajaniworld.model.storage.StorageItemCreation
import es.heathcliff.ajaniworld.service.SpaceService
import io.honeycomb.beeline.tracing.Beeline
import org.springframework.boot.actuate.endpoint.web.EndpointMapping
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.xml.ws.Endpoint

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

    @PreAuthorize(SecurityPolicies.ALLOW_REGISTRED_USER)
    @PostMapping
    fun save(@Valid space: SpaceDto){
        beeline.activeSpan.addField(Constants.LOG_INFO, "findAll hit")
        return spaceService.save(space)
    }

    @PreAuthorize(SecurityPolicies.ALLOW_REGISTRED_USER)
    @PutMapping(EndpointMappings.ID_PARAM + "/images")
    fun addImages(@PathVariable("id") spaceId: String, @RequestBody images: List<StorageItemCreation>){
        spaceService.addImages(spaceId, images)
    }
}