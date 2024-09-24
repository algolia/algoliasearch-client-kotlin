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
        implementation(libs.ktor.client.mock)
      }
    }

    val jvmTest by getting {
      dependencies {
        implementation(libs.kotlin.test.junit)
        implementation(libs.ktor.client.okhttp)
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
