buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.4.31"))
        classpath(kotlin("serialization", version = "1.4.31"))
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
