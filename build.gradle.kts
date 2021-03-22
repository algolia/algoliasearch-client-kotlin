buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        val kotlinVersion = "1.4.31"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
        classpath(MavenPublish())
        classpath(Spotless())
    }
}

project.extensions.extraProperties.apply {
    set("GROUP", Library.group)
    set("VERSION_NAME", Library.version)
}

subprojects {
    repositories {
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
