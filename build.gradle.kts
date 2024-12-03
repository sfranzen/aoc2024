plugins {
    kotlin("jvm") version "2.1.0"
}

group = "com.adventofcode"
version = "2024"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}
