buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        val kotlinVersion = "1.5.20"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.15.1")
        classpath("com.diffplug.spotless:spotless-plugin-gradle:5.14.0")
    }
}

project.extensions.extraProperties.apply {
    set("VERSION_NAME", libs.versions.apiclient.get())
}

subprojects {
    repositories {
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
