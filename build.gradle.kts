import java.net.URI

object Version {

    const val kotlin = "1.3.30-eap-42"
    const val ktor = "1.2.0-alpha-2"
    const val okHttp = "3.11.0"
    const val serialization = "0.11.0-1.3.30-eap-42"
}

plugins {
    id("kotlin-multiplatform") version "1.3.30-eap-45"
    id("kotlinx-serialization") version "1.3.30-eap-45"
    id("maven-publish")
}

version = "1.0.0"
group = "com.algolia.algoliasearch"

repositories {
    jcenter()
    mavenCentral()
    maven { url = URI("https://dl.bintray.com/kotlin/ktor") }
    maven { url = URI("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { url = URI("https://dl.bintray.com/kotlin/kotlin-dev") }
    maven { url = URI("https://kotlin.bintray.com/kotlinx") }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    targets {
        findByName("metadata")?.mavenPublication {
            artifactId = "algoliasearch-client-kotlin-common"
        }
    }
    sourceSets {
        all {
            languageSettings.progressiveMode = true
        }
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Version.serialization}")
                api("io.ktor:ktor-client:${Version.ktor}")
                api("io.ktor:ktor-client-logging:${Version.ktor}")
                api("io.ktor:ktor-client-core:${Version.ktor}")
                api("io.ktor:ktor-client-json:${Version.ktor}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("io.ktor:ktor-client-mock:${Version.ktor}")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                api("io.ktor:ktor-client-okhttp:${Version.ktor}")
                api("io.ktor:ktor-client-core-jvm:${Version.ktor}")
                api("io.ktor:ktor-client-json-jvm:${Version.ktor}")
                api("io.ktor:ktor-client-logging-jvm:${Version.ktor}")
                api("com.squareup.okhttp3:okhttp:${Version.okHttp}")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation("io.ktor:ktor-client-mock-jvm:${Version.ktor}")
            }
        }
    }
}