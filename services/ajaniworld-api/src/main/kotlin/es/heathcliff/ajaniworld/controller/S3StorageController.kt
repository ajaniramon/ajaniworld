package es.heathcliff.ajaniworld.controller

import es.heathcliff.ajaniworld.constants.Constants
import es.heathcliff.ajaniworld.constants.EndpointMappings
import es.heathcliff.ajaniworld.constants.SecurityPolicies
import es.heathcliff.ajaniworld.model.storage.StorageItemCreation
import es.heathcliff.ajaniworld.service.S3StorageService
import io.honeycomb.beeline.tracing.Beeline
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(EndpointMappings.S3_STORAGE)
class S3StorageController(private var s3StorageService: S3StorageService,
                          private var beeline: Beeline) {

    // TODO: Refactor this and return a strong custom model with controlled properties
    @PreAuthorize(SecurityPolicies.ALLOW_ONLY_ADMIN)
    @GetMapping
    fun listObjects(@RequestParam(required = false) folder: String?, @RequestParam(required = false) maxItems: Int?)
            = s3StorageService.listObjects(folder, maxItems)

    @PreAuthorize(SecurityPolicies.ALLOW_ONLY_ADMIN)
    @GetMapping("/items")
    fun getObject(@RequestParam key: String) = s3StorageService.getObject(Constants.AJANIWORLD_DEFAULT_S3_BUCKET, key)

    @PreAuthorize(SecurityPolicies.ALLOW_ONLY_ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/items")
    fun putObject(@RequestBody storageItemCreation: StorageItemCreation){
        s3StorageService.putObject(Constants.COLIVING_SPACES_S3_BUCKET, storageItemCreation)
    }
}