import com.diffplug.gradle.spotless.SpotlessExtension

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
  alias(libs.plugins.kotlin.multiplaform) apply false
  alias(libs.plugins.kotlinx.serialization) apply false
  alias(libs.plugins.kotlinx.binary.validator) apply false
  alias(libs.plugins.maven.publish) apply false
  alias(libs.plugins.spotless) apply false
}

subprojects {
  apply(plugin = "com.diffplug.spotless")
  configure<SpotlessExtension> {
    kotlin {
      target("**/*.kt")
      ktfmt().googleStyle()
    }
  }
}

tasks.register<Delete>("clean") {
  delete(project.layout.buildDirectory)
}
