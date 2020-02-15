package es.heathcliff.ajaniworld.tests

import org.junit.jupiter.api.Test
import org.bouncycastle.cms.RecipientId.password
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.SecureRandom


class PasswordEncoderTests {
    @Test
    fun encodePassword(){
        val password = "microsd1"
        val passwordEncoder = BCryptPasswordEncoder(12, SecureRandom("salt".toByteArray()))
        val hashedPassword = passwordEncoder.encode(password)

        println(hashedPassword)
    }
}