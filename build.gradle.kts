import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import java.net.URI

buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath(plugin.Spotless())
    }
}

plugins {
    kotlin("multiplatform") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
    id("maven-publish")
}

apply(plugin = "com.diffplug.spotless")

repositories {
    jcenter()
    google()
    mavenCentral()
    maven { url = URI("https://dl.bintray.com/kotlin/ktor") }
    maven { url = URI("https://kotlin.bintray.com/kotlinx") }
    maven { url = URI("https://oss.sonatype.org/content/repositories/snapshots") }
}

version = Library.version
group = Library.group

kotlin {
    explicitApi()
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {
        all {
            languageSettings.progressiveMode = true
        }
        val commonMain by getting {
            kotlin.srcDirs("$buildDir/generated/sources/templates/kotlin/main")
            dependencies {
                api(Ktor("client"))
                api(Ktor("client-json"))
                api(Ktor("client-logging"))
                api(Ktor("client-serialization"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-annotations-common"))
                implementation(Ktor("client"))
                implementation(Ktor("client-mock"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(Ktor("client-okhttp"))
                implementation(Ktor("client-apache"))
                implementation(Ktor("client-android"))
            }
        }
    }
}

val javadocJar by tasks.creating(Jar::class) {
    archiveClassifier.value("javadoc")
}

tasks {

    withType<KotlinCompile<*>>().configureEach {
        dependsOn("copyTemplates")
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }

    register(name = "copyTemplates", type = Copy::class) {
        from("src/commonMain/templates")
        into("$buildDir/generated/sources/templates/kotlin/main")
        expand("projectVersion" to Library.version)
        filteringCharset = "UTF-8"
    }

    withType<Test> {
        maxParallelForks = Runtime.getRuntime().availableProcessors().minus(1).coerceAtLeast(1)
    }
}

configure<SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        ktlint("0.40.0")
        trimTrailingWhitespace()
        endWithNewline()
    }
}

//** Publish **//

publishing {
    repositories {
        maven {
            url = uri("https://api.bintray.com/maven/algolia/maven/algoliasearch-client-kotlin/;publish=0")
            credentials {
                username = System.getenv("BINTRAY_USER")
                password = System.getenv("BINTRAY_KEY")
            }
        }
    }
    publications.withType<MavenPublication>().all {
        groupId = Library.group
        version = Library.version
        artifactId = when (name) {
            "kotlinMultiplatform" -> Library.artifact
            else -> "${Library.artifact}-$name"
        }

        pom.withXml {
            asNode().apply {
                appendNode("description",
                    "Algolia is a powerful search-as-a-service solution, made easy to use with API clients, UI libraries," +
                        "and pre-built integrations. Algolia API Client for Kotlin lets you easily use the Algolia Search" +
                        "REST API from your JVM project, such as Android or backend implementations.")
                appendNode("url", "https://github.com/algolia/algoliasearch-client-kotlin")
                appendNode("licenses").appendNode("license").apply {
                    appendNode("name", "MIT")
                    appendNode("url", "http://www.opensource.org/licenses/mit-license.php")
                    appendNode("distribution", "repo")
                }
                appendNode("developers").appendNode("developer").apply {
                    appendNode("id", "algolia")
                    appendNode("name", "The Algolia Team")
                    appendNode("email", "hey@algolia.com")
                }
                appendNode("scm").apply {
                    appendNode("url", "https://github.com/algolia/algoliasearch-client-kotlin.git")
                }
            }
        }
    }

    kotlin.targets.forEach { target ->
        val targetPublication = publications.withType<MavenPublication>().findByName(target.name)
        targetPublication?.artifact(javadocJar)
    }
}
