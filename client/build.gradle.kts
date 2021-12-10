import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.vanniktech.maven.publish")
    id("com.diffplug.spotless")
    id("binary-compatibility-validator")
}

kotlin {
    explicitApi()
    jvm()
    ios()

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
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.mock)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(libs.ktor.client.okhttp)
                implementation(libs.ktor.client.apache)
                implementation(libs.ktor.client.android)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.ios)
            }
        }
    }
}

tasks {
    val copyTemplates by registering(type = Copy::class) {
        from("src/commonMain/templates")
        into("$buildDir/generated/sources/templates/commonMain")
        val version = project.extra["VERSION_NAME"] as String // changes require clean build
        expand("projectVersion" to version)
        filteringCharset = "UTF-8"
    }
    kotlin.sourceSets.commonMain.get().kotlin.srcDir(copyTemplates)

    withType<Test> {
        testLogging {
            events("FAILED")
            setExceptionFormat("FULL")
            showStandardStreams = true
            showStackTraces = true
        }
    }
}

configure<SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        ktlint("0.41.0")
        trimTrailingWhitespace()
        endWithNewline()
    }
}
