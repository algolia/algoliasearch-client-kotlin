import java.net.URI


plugins {
    id("kotlin-multiplatform") version "1.3.30"
    id("kotlinx-serialization") version "1.3.30"
    id("maven-publish")
}

version = Library.version
group = Library.group

repositories {
    jcenter()
    mavenCentral()
    maven { url = URI("https://dl.bintray.com/kotlin/ktor") }
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
    sourceSets {
        all {
            languageSettings.progressiveMode = true
        }
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(Serialization("runtime"))
                api(Ktor("client"))
                api(Ktor("client-logging"))
                api(Ktor("client-core"))
                api(Ktor("client-json"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                api(Ktor("client-mock"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                api(Ktor("client-core-jvm"))
                api(Ktor("client-json-jvm"))
                api(Ktor("client-logging-jvm"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation(Ktor("client-mock-jvm"))
                implementation(Ktor("client-okhttp"))
                implementation(Ktor("client-apache"))
                implementation(Ktor("client-android"))
            }
        }
    }
}