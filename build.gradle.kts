import com.diffplug.gradle.spotless.SpotlessExtension
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.gradle.api.publish.maven.internal.artifact.FileBasedMavenArtifact
import org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon
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
    id("kotlin-multiplatform") version "1.4.0"
    id("kotlinx-serialization") version "1.4.0"
    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.4"
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

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>> {
        kotlinOptions {
            freeCompilerArgs = listOfNotNull("-Xopt-in=kotlin.RequiresOptIn")
        }
    }
}

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

    withType<KotlinCompileCommon> {
        dependsOn("copyTemplates")
    }

    register(name = "copyTemplates", type = Copy::class) {
        from("src/commonMain/templates")
        into("$buildDir/generated/sources/templates/kotlin/main")
        expand("projectVersion" to Library.version)
        filteringCharset = "UTF-8"
    }
}

tasks.withType<Test> {
    maxParallelForks = Runtime.getRuntime().availableProcessors().minus(1).coerceAtLeast(1)
}

configure<SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        ktlint("0.37.2")
        trimTrailingWhitespace()
        endWithNewline()
    }
}

//** Publish **//

publishing {
    repositories {
        maven {
            url = uri("https://dl.bintray.com/algolia/maven")
        }
    }
    publications.withType<MavenPublication>().all {
        groupId = Library.group
        version = Library.version

        pom.withXml {
            asNode().apply {
                appendNode("description", "Kotlin client for Algolia Search API.")
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

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true

    pkg.apply {
        desc = "Algolia is a powerful search-as-a-service solution, made easy to use with API clients, UI libraries," +
            "and pre-built integrations. Algolia API Client for Kotlin lets you easily use the Algolia Search" +
            "REST API from your JVM project, such as Android or backend implementations."
        repo = "maven"
        name = Library.artifact
        websiteUrl = "https://www.algolia.com/"
        issueTrackerUrl = "https://github.com/algolia/algoliasearch-client-kotlin/issues"
        setLicenses("MIT")
        setLabels("Kotlin", "Algolia")
        vcsUrl = "https://github.com/algolia/algoliasearch-client-kotlin.git"
        version.apply {
            name = Library.version
            vcsTag = Library.version
        }
    }
}

// Workaround until Bintray gradle plugin caches up:
// https://github.com/bintray/gradle-bintray-plugin/issues/229#issuecomment-473123891
tasks.withType<BintrayUploadTask> {
    doFirst {
        publishing.publications
            .filterIsInstance<MavenPublication>()
            .forEach { publication ->
                val moduleFile = buildDir.resolve("publications/${publication.name}/module.json")
                if (moduleFile.exists()) {
                    publication.artifact(object : FileBasedMavenArtifact(moduleFile) {
                        override fun getDefaultExtension() = "module"
                    })
                }
            }
    }
}
