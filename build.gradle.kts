import com.android.build.gradle.LibraryExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath(plugin.Android())
        classpath(plugin.Spotless())
    }
}

plugins {
    id("kotlin-multiplatform") version "1.3.72"
    id("kotlinx-serialization") version "1.3.72"
    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.4"
    id("com.github.kukuhyoniatmoko.buildconfigkotlin") version "1.0.5"
}

apply(plugin = "com.android.library")
apply(plugin = "com.diffplug.gradle.spotless")

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

extensions.getByType(LibraryExtension::class.java).apply {
    compileSdkVersion(29)

    defaultConfig {
        minSdkVersion(17)
        targetSdkVersion(29)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions.unitTests.isIncludeAndroidResources = true

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            java.srcDirs("src/androidMain/kotlin")
            res.srcDirs("src/androidMain/res")
        }
        getByName("test") {
            java.srcDirs("src/androidTest/kotlin")
            res.srcDirs("src/androidTest/res")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    testOptions.unitTests.isIncludeAndroidResources = true
}

buildConfigKotlin {
    sourceSet("metadata") {
        buildConfig(name = "version", value = Library.version)
    }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    android {
        mavenPublication {
            artifactId = "${Library.artifact}-android"
        }
        publishLibraryVariants("release")
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
            kotlin.srcDirs("build/generated")
            dependencies {
                api(kotlin("stdlib-common"))
                api(Ktor("client"))
                api(Ktor("client-logging"))
                api(Ktor("client-core"))
                api(Ktor("client-json"))
                api(Ktor("client-serialization"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                api(Ktor("client-mock"))
            }
        }
        val jvmMain by getting {
            dependencies {
                api(kotlin("stdlib-jdk8"))
                api(Ktor("client-core-jvm"))
                api(Ktor("client-json-jvm"))
                api(Ktor("client-logging-jvm"))
                api(Ktor("client-serialization-jvm"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation(Ktor("client-mock-jvm"))
                implementation(Ktor("client-okhttp"))
                implementation(Ktor("client-apache"))
                implementation(Ktor("client-android"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(kotlin("stdlib-jdk8"))
                api(Ktor("client-core-jvm"))
                api(Ktor("client-json-jvm"))
                api(Ktor("client-logging-jvm"))
                api(Ktor("client-serialization-jvm"))
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation(Ktor("client-mock-jvm"))
                implementation(Ktor("client-android"))
                implementation(AndroidTestRunner())
                implementation(AndroidTestExtRunner())
                implementation(Robolectric())
            }
        }
    }
}

val javadocJar by tasks.creating(Jar::class) {
    archiveClassifier.value("javadoc")
}

publishing {
    repositories {
        maven {
            url = uri("https://dl.bintray.com/algolia/maven")
        }
    }
    publications.withType<MavenPublication>().all {
        groupId = Library.group
        version = Library.version
        artifactId = when (name) {
            "kotlinMultiplatform" -> Library.artifact
            "metadata" -> "${Library.artifact}-common"
            else -> "${Library.artifact}-$name"
        }

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

tasks {
    val bintrayUpload by getting(BintrayUploadTask::class) {
        doFirst {
            setPublications("jvm", "metadata", "androidRelease")
        }
    }
    withType<KotlinCompile> {
        dependsOn("generateMetadataBuildConfigKotlin")
    }
}

tasks.withType<Test> {
    maxParallelForks = Runtime.getRuntime().availableProcessors().minus(1).coerceAtLeast(1)
}

configure<SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        ktlint("0.36.0").userData(mapOf(
            // Disable Ktlint import ordering temporarily.
            // https://github.com/pinterest/ktlint/issues/527
            "disabled_rules" to "import-ordering"
        ))
        trimTrailingWhitespace()
        endWithNewline()
    }
}
