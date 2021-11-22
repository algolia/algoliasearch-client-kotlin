import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest

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
                implementation("org.jetbrains.kotlinx:atomicfu:0.17.0")
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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2-native-mt")
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
}

fun KotlinMultiplatformExtension.darwin() {
    val targets = mutableListOf<KotlinNativeTarget>().apply {
        add(iosArm32())
        add(iosArm64())
        add(iosX64())
        add(macosArm64())
        add(macosX64())
        add(tvosArm64())
        add(tvosX64())
        add(watchosArm32())
        add(watchosArm64())
        add(watchosX64())
        add(watchosX86())
        add(iosSimulatorArm64())
        add(tvosSimulatorArm64())
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

    // TODO: remove
    val macosX64Test by getting(KotlinNativeTest::class) {
        testLogging {
            events("FAILED")
            exceptionFormat = TestExceptionFormat.FULL
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
