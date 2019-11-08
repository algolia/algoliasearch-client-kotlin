import java.net.URI

pluginManagement {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = URI("https://plugins.gradle.org/m2/") }
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "kotlin-multiplatform") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
            if (requested.id.id == "kotlinx-serialization") {
                useModule("org.jetbrains.kotlin:kotlin-serialization:${requested.version}")
            }
        }
    }
}

enableFeaturePreview("GRADLE_METADATA")

rootProject.name = "algoliasearch-client-kotlin"
