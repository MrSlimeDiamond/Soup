plugins {
    id("java")
}

group = "net.slimediamond"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.zenoc.net/repository")
}

dependencies {
    implementation("org.galliumpowered:gallium:1.1.0-beta.4")
    implementation("org.apache.logging.log4j:log4j-core:2.19.0")
    implementation("org.apache.logging.log4j:log4j-api:2.19.0")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.19.0")
    implementation("org.slf4j:slf4j-api:2.0.1")
    implementation("com.google.guava:guava:32.0.1-jre")
    implementation("com.google.inject:guice:7.0.0")
    implementation("net.kyori:adventure-api:4.2.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "net.slimediamond.soup.Soup")
    }
}