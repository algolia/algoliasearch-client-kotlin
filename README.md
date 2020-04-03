<p align="center">
  <a href="https://www.algolia.com">
    <img alt="Algolia for Kotlin" src="https://raw.githubusercontent.com/algolia/algoliasearch-client-common/master/banners/kotlin.png" >
  </a>
  
  <h4 align="center">The perfect starting point to integrate <a href="https://algolia.com" target="_blank">Algolia</a> within your Kotlin project</h4>

  <p align="center">
    <a href="https://bintray.com/algolia/maven/algoliasearch-client-kotlin/_latestVersion"><img src="https://api.bintray.com/packages/algolia/maven/algoliasearch-client-kotlin/images/download.svg" alt="Latest version"></img></a>
    <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="Licence"></img></a>
  </p>
</p>

<p align="center">
  <a href="https://www.algolia.com/doc/api-client/getting-started/install/kotlin/" target="_blank">Documentation</a>  •
  <a href="https://discourse.algolia.com" target="_blank">Community Forum</a>  •
  <a href="http://stackoverflow.com/questions/tagged/algolia" target="_blank">Stack Overflow</a>  •
  <a href="https://github.com/algolia/algoliasearch-client-kotlin/issues" target="_blank">Report a bug</a>  •
  <a href="https://www.algolia.com/support" target="_blank">Support</a>
</p>

## ✨ Features

- The Kotlin client is compatible with Kotlin `1.3.70` and higher.
- It is compatible with Kotlin project on the JVM, such as backend and Android applications.
- It relies on the open source Kotlin libraries for seamless integration into Kotlin projects:
  - [Kotlin multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html).
  - [Kotlinx serialization](https://github.com/Kotlin/kotlinx.serialization) for json parsing.
  - [Kotlinx coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous operations.
  - [Ktor](https://github.com/ktorio/ktor) HTTP client.
- The Kotlin client integrates the actual Algolia documentation in each source file: Request parameters, response fields, methods and concepts; all are documented and link to the corresponding url of the Algolia doc website.
- The client is thread-safe. You can use `SearchClient`, `AnalyticsClient`, and `InsightsClient` in a multithreaded environment.

## 💡 Getting Started

Install the Kotlin client by adding the following dependency to your `gradle.build` file:

  ```gradle
  repositories {
     maven { url "https://dl.bintray.com/algolia/maven" }
  }
  
  dependencies {
     // Search API Client
     implementation "com.algolia:algoliasearch-client-kotlin-jvm:$kotlin_client_version"
     // alternately - for android, use the following
     implementation "com.algolia:algoliasearch-client-kotlin-android:$kotlin_client_version"

     // Choose one of the following http client
     implementation "io.ktor:ktor-client-apache:$ktor_version"
     implementation "io.ktor:ktor-client-okhttp:$ktor_version"
     implementation "io.ktor:ktor-client-android:$ktor_version"
     implementation "io.ktor:ktor-client-cio:$ktor_version"
     implementation "io.ktor:ktor-client-jetty:$ktor_version"
  }
  ```

For full documentation, visit the **[Algolia Kotlin API Client](https://www.algolia.com/doc/api-client/getting-started/install/kotlin/)**.

_ ⚠️ Important: starting from version `1.4.0` the library is compatible only with kotlin version `1.3.70` or higher; for previous versions of kotlin, please use version `1.3.1` of the library._

### Coroutines

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


### Waiting for operations

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

### Type safety

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

### Proguard rules

When proguard `minifyEnabled` option is set to `true` , you might get this error:

```
Can't locate argument-less serializer for class e.a.b.g.n.c (Kotlin reflection is not available). For generic classes, such as lists, please provide serializer explicitly.
```

Add this proguard rule to solve it.

```
-keep class com.algolia.search.model.** { *; }
```

### Guides

- [Getting started](https://github.com/algolia/algoliasearch-client-kotlin/tree/master/docs/GettingStarted.md)
- [DSL](https://github.com/algolia/algoliasearch-client-kotlin/tree/master/docs/DSL.md)
- [Serialization](https://github.com/algolia/algoliasearch-client-kotlin/tree/master/docs/Serialization.md)
- [ExceptionHandling](https://github.com/algolia/algoliasearch-client-kotlin/tree/master/docs/ExceptionHandling.md)
- [Configure the HTTP client](https://github.com/algolia/algoliasearch-client-kotlin/tree/master/docs/HTTPClient.md)

## 📄 License

Algolia Kotlin API Client is an open-sourced software licensed under the [MIT license](LICENSE.md).
