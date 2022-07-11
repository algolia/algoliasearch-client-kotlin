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
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
        val commonMain by getting {
            dependencies {
                api(libs.ktor.client.core)
                api(libs.kotlinx.serialization.json)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.serialization.json)
                implementation(libs.ktor.client.content.negotiation)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test.common)
                implementation(libs.kotlin.test.annotations.common)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.ktor.client.mock)
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(libs.kotlin.test.junit)
                implementation(libs.ktor.client.apache)
                implementation(libs.ktor.client.okhttp)
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
        trimTrailingWhitespace()
        endWithNewline()
    }
}
