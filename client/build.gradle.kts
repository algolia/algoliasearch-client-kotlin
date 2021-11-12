import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    js(IR) {
        useCommonJs()
        browser()
    }

    val iosX64 = iosX64()
    val iosArm32 = iosArm32()
    val iosArm64 = iosArm64()
    val tvosX64 = tvosX64()
    val tvosArm64 = tvosArm64()
    val watchosX86 = watchosX86()
    val watchosX64 = watchosX64()
    val watchosArm32 = watchosArm32()
    val watchosArm64 = watchosArm64()
    val macosX64 = macosX64()
    //val macosArm64 = macosArm64()
    //val iosSimulatorArm64 = iosSimulatorArm64()
    //val watchosSimulatorArm64 = watchosSimulatorArm64()
    //val tvosSimulatorArm64 = tvosSimulatorArm64()

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
                api(libs.ktor.client.okhttp)
            }
        }
        val darwinMain by creating {
            dependsOn(commonMain)
        }
        val darwinTest by creating {
            dependsOn(commonTest)
        }
        configure(listOf(iosX64, iosArm32, iosArm64, tvosX64, tvosArm64, watchosX86, watchosX64, watchosArm32, watchosArm64, macosX64)) {
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
}

configure<SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        ktlint("0.41.0")
        trimTrailingWhitespace()
        endWithNewline()
    }
}
