package es.heathcliff.ajaniworld.service

import es.heathcliff.ajaniworld.constants.Constants
import es.heathcliff.ajaniworld.domain.model.Role
import es.heathcliff.ajaniworld.domain.model.User
import es.heathcliff.ajaniworld.exception.ColivingError
import es.heathcliff.ajaniworld.exception.ColivingException
import es.heathcliff.ajaniworld.mapper.map
import es.heathcliff.ajaniworld.mapper.mapFrom
import es.heathcliff.ajaniworld.model.UserAvatar
import es.heathcliff.ajaniworld.model.UserDto
import es.heathcliff.ajaniworld.repository.RoleRepository
import es.heathcliff.ajaniworld.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService(private var userRepository: UserRepository, private var passwordEncoder: PasswordEncoder,
                  private var roleRepository: RoleRepository) {

    fun findAll(): List<UserDto> = userRepository.findAll().map(::map)

    fun findByCode(code: String) : User? = userRepository.findByCode(code)

    fun save(userDto: UserDto){
        if(userRepository.existsByCode(userDto.code) || userRepository.existsByEmail(userDto.email)){
            throw ColivingException(listOf(ColivingError(Constants.USER_FIELD_NAME, Constants.ERROR_ALREADY_EXISTS)))
        }

        val user = mapFrom(userDto)

        // add user the regular USER role
        val userRoleFromDb = roleRepository.findByName(Constants.USER_ROLE_NAME)

        // double bang because role is supposed to be on DB
        user.roles.add(userRoleFromDb!!)

        user.password = passwordEncoder.encode(userDto.password)
        userRepository.save(user)
    }

    fun login(userName: String, password: String) : Boolean{
        val userFromDb = userRepository.findByCode(userName) ?: return false

        return passwordEncoder.matches(password, userFromDb.password)
    }

    fun getCurrentUser() : UserDto{
        val currentUserCode = SecurityContextHolder.getContext().authentication.principal as String

        // double bang because user is logged hence is present in DB
        return map(userRepository.findByCode(currentUserCode)!!)
    }

    fun addRole(userId: String, roleId: String){
        val user: User = userRepository.getOne(userId)
        val role: Role = roleRepository.getOne(roleId)

        user.roles.add(role)

        userRepository.save(user)
    }

    @Transactional
    fun changeUserAvatar(userId: String, avatar: UserAvatar){
        if(avatar.avatar.size > Constants.MAX_AVATAR_LENGTH){
            throw ColivingException(listOf(ColivingError(Constants.AVATAR_FIELD_NAME, Constants.ERROR_MAX_SIZE_EXCEEDED)))
        }

        val user = userRepository.findByIdOrNull(userId)

        if(user == null){
            throw ColivingException(listOf(ColivingError(Constants.USER_FIELD_NAME, Constants.ERROR_NOT_EXISTS)))
        }else{
            user.avatar = avatar.avatar
            userRepository.save(user)
        }
    }

}