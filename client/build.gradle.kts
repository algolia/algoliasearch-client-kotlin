import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.vanniktech.maven.publish")
    id("com.diffplug.spotless")
    id("binary-compatibility-validator")
}

kotlin {
    explicitApi()
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
            testLogging {
                events("failed")
                setExceptionFormat("full")
            }
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
            }
        }
        val commonMain by getting {
            dependencies {
                api(libs.ktor.client.core)
                api(libs.ktor.client.json)
                api(libs.ktor.client.logging)
                api(libs.ktor.client.serialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                api(libs.ktor.client.core)
                api(libs.ktor.client.mock)
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                api(libs.ktor.client.apache)
                api(libs.ktor.client.okhttp)
                api(libs.ktor.client.android)
            }
        }
    }
}

tasks {
    val copyTemplates by registering(type = Copy::class) {
        from("src/commonMain/templates")
        val outputDir = "$buildDir/generated/sources/templates/kotlin/main"
        into(outputDir)
        val version = project.extensions.extraProperties["VERSION_NAME"] as String // require clean build
        expand("projectVersion" to version)
        filteringCharset = "UTF-8"
    }

    kotlin.sourceSets.commonMain.get().kotlin.srcDir(copyTemplates)
}

configure<SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        ktlint("0.41.0")
        trimTrailingWhitespace()
        endWithNewline()
    }
}
