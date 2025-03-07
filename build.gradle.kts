plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
	id ("jacoco" )// Add JaCoCo plugin
}

group = "com.exchange"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-security")
	implementation ("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation ("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation ("io.jsonwebtoken:jjwt-jackson:0.11.5")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation ("org.mockito:mockito-core")
	testImplementation ("org.mockito:mockito-junit-jupiter")
	testImplementation ("org.junit.jupiter:junit-jupiter-api")
	testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine")

}

jacoco {
	toolVersion = "0.8.11"  // Use latest JaCoCo version
}
tasks.withType<Test> {
	useJUnitPlatform()

}

tasks.jacocoTestReport {
	dependsOn(tasks.test) // Ensure tests run before generating the report

	reports {
		xml.required.set(false) // XML report for CI/CD integration
		csv.required.set(false)
		html.required.set(true) // HTML report for local analysis
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
	}
}