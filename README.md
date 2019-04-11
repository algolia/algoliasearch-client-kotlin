# AlgoliaSearch Client Kotlin

[![CircleCI](https://circleci.com/gh/algolia/algoliasearch-client-kotlin.svg?style=svg&circle-token=2c208a898fb41bde4697c584e8d5871ee279695c)](https://circleci.com/gh/algolia/algoliasearch-client-kotlin)

## Installation

Install the Kotlin client by adding the following dependency to your `Gradle` build file:

  ```gradle
  dependencies {
      // [...]
      implementation 'com.algolia:algoliasearch-android:3.+'
      // This will automatically update to the latest v3 release when you build your project
  }
  ```

### Supported platforms

The Kotlin client is compatible with the JVM. It is recommended for the following use cases:
 - Frontend Android projects using Kotlin
 - Backend JVM projects using Kotlin
The Kotlin client architecture is designed with [Kotlin multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html) in mind.
However, we don't support Kotlin native and Kotlin js targets just yet.


## Guides

- [Philosophy](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/Philosophy.md)
