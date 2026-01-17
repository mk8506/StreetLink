plugins {
	java
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "io.sunhacks"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// MyBatis
  implementation("org.mybatis:mybatis:3.5.16")
	implementation("org.mybatis:mybatis-spring:3.0.4")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")

	// Lombok
	annotationProcessor("org.projectlombok:lombok:1.18.34")
	compileOnly("org.projectlombok:lombok:1.18.34")

	// MySQL
	implementation("com.mysql:mysql-connector-j:9.0.0")
	runtimeOnly("mysql:mysql-connector-java")

	// Log4jdbc
	implementation("org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
