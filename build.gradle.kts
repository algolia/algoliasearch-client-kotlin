buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        val kotlinVersion = "1.5.30"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.18.0")
        classpath("com.diffplug.spotless:spotless-plugin-gradle:5.15.0")
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
