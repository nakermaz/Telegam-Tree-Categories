plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example.telegacategories"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.flywaydb:flyway-core")
	implementation("org.telegram:telegrambots:6.9.7.1")
	implementation("org.springframework.boot:spring-boot-starter:3.2.5")
	implementation("org.hibernate:hibernate-core:6.5.1.Final")
	implementation("org.hibernate.orm:hibernate-core:6.5.1.Final")
	implementation("javax.xml.bind:jaxb-api:2.3.1")
	implementation("org.telegram:telegrambots-spring-boot-starter:6.9.7.1")
	implementation("org.postgresql:postgresql:42.7.3")
	compileOnly("org.projectlombok:lombok:1.18.32")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
