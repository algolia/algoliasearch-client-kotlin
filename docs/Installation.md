# Installation

Install the Kotlin client by adding the following dependency to your `Gradle` build file:

```gradle
dependencies {
  implementation "com.algolia:algoliasearch-client-kotlin-jvm:$kotlin_client_version"
}
```

## Supported platforms

The Kotlin client is compatible with the JVM. It is recommended for the following use cases:
 - Frontend Android projects using Kotlin
 - Backend JVM projects using Kotlin
The Kotlin client architecture is designed with [Kotlin multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html) in mind.
However, we don't support Kotlin native and Kotlin js targets just yet.
