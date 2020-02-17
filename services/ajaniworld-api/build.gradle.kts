import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
	id("org.springframework.boot") version "2.2.0.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("com.palantir.docker") version "0.25.0"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
	kotlin("plugin.jpa") version "1.3.50"
}

group = "es.heathcliff"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.amazonaws:aws-java-sdk-s3:1.11.723")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.2")
	implementation("io.springfox:springfox-swagger2:2.7.0")
    implementation("org.springframework.boot:spring-boot-starter-security:2.2.1.RELEASE")
	implementation("org.springframework.security:spring-security-jwt:1.0.11.RELEASE")
	implementation("org.springframework.security.oauth:spring-security-oauth2:2.3.7.RELEASE")
	implementation("io.springfox:springfox-swagger-ui:2.7.0")
	implementation("io.jsonwebtoken:jjwt:0.7.0")
	implementation ("io.honeycomb.beeline:beeline-spring-boot-starter:1.0.7")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")

	}

}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
