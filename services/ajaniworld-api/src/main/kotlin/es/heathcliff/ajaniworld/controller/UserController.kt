package es.heathcliff.ajaniworld.controller

import es.heathcliff.ajaniworld.constants.Constants
import es.heathcliff.ajaniworld.constants.EndpointMappings
import es.heathcliff.ajaniworld.constants.SecurityPolicies
import es.heathcliff.ajaniworld.domain.model.User
import es.heathcliff.ajaniworld.model.UserAvatar
import es.heathcliff.ajaniworld.model.UserDto
import es.heathcliff.ajaniworld.service.UserService
import io.honeycomb.beeline.tracing.Beeline
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping(EndpointMappings.USERS)
class UserController(
        private var userService: UserService,
        private var beeline: Beeline
) {
    @PreAuthorize(SecurityPolicies.ALLOW_ONLY_ADMIN)
    @GetMapping
    fun findAll(): List<UserDto> {
        beeline.activeSpan.addField(Constants.LOG_INFO, "findAll hit")
        return userService.findAll()
    }

    @PreAuthorize(SecurityPolicies.ALLOW_REGISTRED_USER)
    @GetMapping("current")
    fun getCurrentUser() : UserDto{
        return userService.getCurrentUser()
    }

    @PostMapping
    fun save(@Valid @RequestBody user: UserDto){
        beeline.activeSpan.addField(Constants.LOG_INFO, "save hit")
        return userService.save(user)
    }

    @PostMapping(EndpointMappings.ID_PARAM + "/roles/{roleId}")
    fun addRole(@PathVariable("id") userId : String, @PathVariable("roleId") roleId: String){
        beeline.activeSpan.addField(Constants.LOG_INFO, "addRole hit")
        userService.addRole(userId, roleId)
    }

    @PutMapping(EndpointMappings.ID_PARAM + "/avatars")
    fun changeUserAvatar(@PathVariable("id") userId: String, @RequestBody avatar: UserAvatar){
        userService.changeUserAvatar(userId, avatar)
    }
}