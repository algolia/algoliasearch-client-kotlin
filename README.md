# AlgoliaSearch Client Kotlin

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


## Guides

- [Philosophy](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/Philosophy.md)
- [DSL](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/DSL.md)
- [Serialization](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/Serialization.md)
- [ExceptionHandling](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/ExceptionHandling.md)
- [Configure the HTTP client](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/HTTPClient.md)
