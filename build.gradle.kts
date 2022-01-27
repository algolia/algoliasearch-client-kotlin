buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        val kotlinVersion = "1.5.32"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.18.0")
        classpath("com.diffplug.spotless:spotless-plugin-gradle:5.15.0")
        classpath("org.jetbrains.kotlinx:binary-compatibility-validator:0.8.0")
    }
}

subprojects {
    repositories {
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
