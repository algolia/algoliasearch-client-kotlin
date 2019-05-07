# AlgoliaSearch Client Kotlin

[ ![Download](https://api.bintray.com/packages/algolia/maven/algoliasearch-client-kotlin/images/download.svg) ](https://bintray.com/algolia/maven/algoliasearch-client-kotlin/_latestVersion)

## Compatibility

The Kotlin client is compatible with Kotlin `1.3.30` and higher.

## Installation

Install the Kotlin client by adding the following dependency to your `gradle.build` file:

  ```gradle
  dependencies {
     implementation "com.algolia:algoliasearch-client-kotlin-jvm:$kotlin_client_version"
     // Choose one of the following http client
     implementation "io.ktor:ktor-client-apache:$ktor_version"
     implementation "io.ktor:ktor-client-okhttp:$ktor_version"
     implementation "io.ktor:ktor-client-android:$ktor_version"
     implementation "io.ktor:ktor-client-cio:$ktor_version"
     implementation "io.ktor:ktor-client-jetty:$ktor_version"
  }
  ```

### Supported platforms

The Kotlin client is compatible with the JVM. It is recommended for the following use cases:
 - Frontend Android projects using Kotlin
 - Backend JVM projects using Kotlin
The Kotlin client architecture is designed with [Kotlin multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html) in mind.
However, we don't support Kotlin native and Kotlin js targets just yet.

## Stack

This client provides a Kotlin implentation for using AlgoliaSearch.
This client is built using the official Kotlin stack:
  - [Kotlin multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html).
  - [Kotlinx serialization](https://github.com/Kotlin/kotlinx.serialization) for json parsing.
  - [Kotlinx coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous operations.
  - [Ktor](https://github.com/ktorio/ktor) HTTP client.

## Coroutines

All methods performing HTTP calls in the Kotlin client are [suspending functions](https://kotlinlang.org/docs/reference/coroutines/composing-suspending-functions.html#composing-suspending-functions). 
This means these functions can only be called from a coroutine.

In the example below, a coroutine is launched in the main thread.
The context is switched to a thread pool to perform the search HTTP call off the main thread.
The response can be manipulated from the main thread.

```kotlin
class Searcher : CoroutineScope {

    override val coroutineContext = Job()

    fun search() {
        launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.Default) { index.search() }
        }
    }
}
```

The developer is responsible for implementing the asynchronous logic that matches their specific requirements.
The Kotlin client doesn't execute HTTP calls on any particular thread, it is up to the developer to define it explicitly using coroutines.
Learn more about [coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html).


## Waiting for operations

Waiting for an asynchronous server task is made available via a [function literal with receiver](https://kotlinlang.org/docs/reference/lambdas.html#function-literals-with-receiver).

Use the [apply](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/apply.html) or [run](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/run.html) functions on your index or client.

```kotlin
index.apply {
    setSettings(Settings()).wait()
}
client.run {
    multipleBatchObjects(listOf<BatchOperationIndex>()).waitAll()
}
```

The `wait` functions are suspending, and should only be called from a coroutine.

## Multithreading

The client is designed to be thread-safe. You can use `SearchClient`, `AnalyticsClient`, and `InsightsClient` in a multithreaded environment.

## Type safety

Response and parameters objects are typed to provide extensive compile time safety coverage.

Example for creating a Client instance without mixing the application ID and the API key.

```kotlin
val appID = ApplicationID("YourApplicationID")
val apiKey = APIKey("YourAdminAPIKey")

val client = ClientSearch(appID, apiKey)
```

Example for attributes:

```kotlin
val color = Attribute("color")
val category = Attribute("category")

Query(
  attributesToRetrieve = listOf(color, category)
)
```

Sealed class are used to represent enumerated types. It allows to quickly discover possible values for each type thanks to IDE autocomplete support.
All sealed class have an `Other` class case for avoiding runtime crashes in case of unforeseen value.

```kotlin
val query = Query()

query.sortFacetsBy = SortFacetsBy.Count
// query.sortFacetsBy = SortFacetsBy.Alpha
// query.sortFacetsBy = SortFacetsBy.Other("unforeseen value")
```


## Guides

- [DSL](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/DSL.md)
- [Serialization](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/Serialization.md)
- [ExceptionHandling](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/ExceptionHandling.md)
- [Configure the HTTP client](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/HTTPClient.md)
