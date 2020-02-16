package es.heathcliff.ajaniworld

import es.heathcliff.ajaniworld.configuration.H2TestProfileJPAConfig
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [AjaniworldApplication::class, H2TestProfileJPAConfig::class])
@ActiveProfiles("test")
class AjaniworldApplicationTests {

	@Test
	fun contextLoads() {
	}

}
