import org.jetbrains.kotlin.konan.target.HostManager

plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("com.vanniktech.maven.publish")
  id("com.diffplug.spotless")
  id("binary-compatibility-validator")
}

kotlin {
  applyDefaultHierarchyTemplate()

  explicitApi()
  jvm()

  if (HostManager.hostIsMac) {
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosArm64()
    macosX64()
  }

  sourceSets {
    all {
      languageSettings {
        optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
        optIn("kotlinx.serialization.ExperimentalSerializationApi")
        optIn("kotlin.io.encoding.ExperimentalEncodingApi")
      }
    }
    val commonMain by getting {
      dependencies {
        api(libs.ktor.client.core)
        api(libs.kotlinx.serialization.json)
        api(libs.ktor.client.logging)
        implementation(libs.ktor.client.serialization.json)
        implementation(libs.ktor.client.content.negotiation)
        implementation(libs.kotlin.datetime)
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

    val jvmMain by getting {
      dependencies {
        implementation(libs.ktor.client.encoding)
      }
    }

    val jvmTest by getting {
      dependencies {
        implementation(libs.kotlin.test.junit)
        implementation(libs.ktor.client.okhttp)
        implementation(libs.ktor.client.cio)
        implementation(libs.ktor.client.java)
        implementation(libs.ktor.client.apache5)
        implementation(libs.ktor.client.apache)
        implementation(libs.ktor.client.android)
        implementation(libs.ktor.client.jetty)
        implementation(libs.ktor.server.jetty)
      }
    }

    if (HostManager.hostIsMac) {
      val appleTest by getting {
        dependencies {
          implementation(libs.ktor.client.darwin)
        }
      }
    }
  }
}
