import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    kotlin("native.cocoapods")
    id("com.vanniktech.maven.publish")
    id("com.diffplug.spotless")
    id("binary-compatibility-validator")
}

kotlin {
    explicitApi()

    jvm()
    darwin()

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

        val darwinTest by getting {
            dependencies {
                implementation(libs.ktor.client.ios)
            }
        }
    }

    cocoapods {
        summary = properties["POM_NAME"].toString()
        homepage = properties["POM_URL"].toString()

        ios.deploymentTarget = "9.0"
        osx.deploymentTarget = "10.10"
        tvos.deploymentTarget = "9.0"
        watchos.deploymentTarget = "2.0"

        pod("GZIP") {
            version = "~> 1.3.0"
        }
    }
}

fun KotlinMultiplatformExtension.darwin() {
    val targets = mutableListOf<KotlinNativeTarget>().apply {
        add(iosArm32())
        add(iosArm64())
        add(iosX64())
        add(iosSimulatorArm64())
        add(macosArm64())
        add(macosX64())
        add(tvosArm64())
        add(tvosX64())
        add(tvosSimulatorArm64())
        add(watchosArm32())
        add(watchosArm64())
        add(watchosX64())
        add(watchosX86())
        add(watchosSimulatorArm64())
    }
    kotlin.sourceSets.apply {
        val darwinMain by creating { dependsOn(getByName("commonMain")) }
        val darwinTest by creating { dependsOn(getByName("commonTest")) }
        configure(targets) {
            sourceSets.getByName("${name}Main").dependsOn(darwinMain)
            sourceSets.getByName("${name}Test").dependsOn(darwinTest)
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
