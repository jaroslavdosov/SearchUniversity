import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "2.0.0"
    id("org.flywaydb.flyway") version "10.15.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.flywaydb:flyway-database-postgresql:10.1.0")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.postgresql:postgresql:42.3.1")

    implementation("io.ktor:ktor-client-core:2.3.11")
    implementation("io.ktor:ktor-client-cio:2.3.11")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.11")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.11")
    implementation("org.jetbrains.exposed:exposed-core:0.52.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.52.0")
    implementation("org.jetbrains.exposed:exposed-json:0.52.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.52.0")

}

flyway {
    url = "jdbc:postgresql://localhost:5442/otus"
    user = "otus"
    password = "otus"
}


tasks.test {
    useJUnitPlatform()
}

