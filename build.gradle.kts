plugins {
    kotlin("jvm") version "2.1.0"
}

group = "com.adventofcode"
version = "2024"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}