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
  
## Integrated documentation

The Kotlin client integrates the actual Algolia documentation in each source file: Request parameters, response fields, methods and concepts; all are documented and link to the corresponding url of the Algolia doc website. 


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

- [Philosophy](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/Philosophy.md)
- [DSL](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/DSL.md)
- [Serialization](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/Serialization.md)
- [ExceptionHandling](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/ExceptionHandling.md)
- [Configure the HTTP client](https://github.com/algolia/algoliasearch-client-kotlin/tree/develop/docs/HTTPClient.md)

## List of available methods





### Personalization

- [Add strategy](https://algolia.com/doc/api-reference/api-methods/add-strategy/?language=kotlin)
- [Get strategy](https://algolia.com/doc/api-reference/api-methods/get-strategy/?language=kotlin)




### Search

- [Search index](https://algolia.com/doc/api-reference/api-methods/search/?language=kotlin)
- [Search for facet values](https://algolia.com/doc/api-reference/api-methods/search-for-facet-values/?language=kotlin)
- [Search multiple indices](https://algolia.com/doc/api-reference/api-methods/multiple-queries/?language=kotlin)
- [Browse index](https://algolia.com/doc/api-reference/api-methods/browse/?language=kotlin)




### Indexing

- [Add objects](https://algolia.com/doc/api-reference/api-methods/add-objects/?language=kotlin)
- [Save objects](https://algolia.com/doc/api-reference/api-methods/save-objects/?language=kotlin)
- [Partial update objects](https://algolia.com/doc/api-reference/api-methods/partial-update-objects/?language=kotlin)
- [Delete objects](https://algolia.com/doc/api-reference/api-methods/delete-objects/?language=kotlin)
- [Replace all objects](https://algolia.com/doc/api-reference/api-methods/replace-all-objects/?language=kotlin)
- [Delete by](https://algolia.com/doc/api-reference/api-methods/delete-by/?language=kotlin)
- [Clear objects](https://algolia.com/doc/api-reference/api-methods/clear-objects/?language=kotlin)
- [Get objects](https://algolia.com/doc/api-reference/api-methods/get-objects/?language=kotlin)
- [Custom batch](https://algolia.com/doc/api-reference/api-methods/batch/?language=kotlin)




### Settings

- [Get settings](https://algolia.com/doc/api-reference/api-methods/get-settings/?language=kotlin)
- [Set settings](https://algolia.com/doc/api-reference/api-methods/set-settings/?language=kotlin)
- [Copy settings](https://algolia.com/doc/api-reference/api-methods/copy-settings/?language=kotlin)




### Manage indices

- [List indices](https://algolia.com/doc/api-reference/api-methods/list-indices/?language=kotlin)
- [Delete index](https://algolia.com/doc/api-reference/api-methods/delete-index/?language=kotlin)
- [Copy index](https://algolia.com/doc/api-reference/api-methods/copy-index/?language=kotlin)
- [Move index](https://algolia.com/doc/api-reference/api-methods/move-index/?language=kotlin)




### API keys

- [Create secured API Key](https://algolia.com/doc/api-reference/api-methods/generate-secured-api-key/?language=kotlin)
- [Add API Key](https://algolia.com/doc/api-reference/api-methods/add-api-key/?language=kotlin)
- [Update API Key](https://algolia.com/doc/api-reference/api-methods/update-api-key/?language=kotlin)
- [Delete API Key](https://algolia.com/doc/api-reference/api-methods/delete-api-key/?language=kotlin)
- [Restore API Key](https://algolia.com/doc/api-reference/api-methods/restore-api-key/?language=kotlin)
- [Get API Key permissions](https://algolia.com/doc/api-reference/api-methods/get-api-key/?language=kotlin)
- [List API Keys](https://algolia.com/doc/api-reference/api-methods/list-api-keys/?language=kotlin)




### Synonyms

- [Save synonym](https://algolia.com/doc/api-reference/api-methods/save-synonym/?language=kotlin)
- [Batch synonyms](https://algolia.com/doc/api-reference/api-methods/batch-synonyms/?language=kotlin)
- [Delete synonym](https://algolia.com/doc/api-reference/api-methods/delete-synonym/?language=kotlin)
- [Clear all synonyms](https://algolia.com/doc/api-reference/api-methods/clear-synonyms/?language=kotlin)
- [Get synonym](https://algolia.com/doc/api-reference/api-methods/get-synonym/?language=kotlin)
- [Search synonyms](https://algolia.com/doc/api-reference/api-methods/search-synonyms/?language=kotlin)
- [Replace all synonyms](https://algolia.com/doc/api-reference/api-methods/replace-all-synonyms/?language=kotlin)
- [Copy synonyms](https://algolia.com/doc/api-reference/api-methods/copy-synonyms/?language=kotlin)
- [Export Synonyms](https://algolia.com/doc/api-reference/api-methods/export-synonyms/?language=kotlin)




### Query rules

- [Save rule](https://algolia.com/doc/api-reference/api-methods/save-rule/?language=kotlin)
- [Batch rules](https://algolia.com/doc/api-reference/api-methods/batch-rules/?language=kotlin)
- [Get rule](https://algolia.com/doc/api-reference/api-methods/get-rule/?language=kotlin)
- [Delete rule](https://algolia.com/doc/api-reference/api-methods/delete-rule/?language=kotlin)
- [Clear rules](https://algolia.com/doc/api-reference/api-methods/clear-rules/?language=kotlin)
- [Search rules](https://algolia.com/doc/api-reference/api-methods/search-rules/?language=kotlin)
- [Replace all rules](https://algolia.com/doc/api-reference/api-methods/replace-all-rules/?language=kotlin)
- [Copy rules](https://algolia.com/doc/api-reference/api-methods/copy-rules/?language=kotlin)
- [Export rules](https://algolia.com/doc/api-reference/api-methods/export-rules/?language=kotlin)




### A/B Test

- [Add A/B test](https://algolia.com/doc/api-reference/api-methods/add-ab-test/?language=kotlin)
- [Get A/B test](https://algolia.com/doc/api-reference/api-methods/get-ab-test/?language=kotlin)
- [List A/B tests](https://algolia.com/doc/api-reference/api-methods/list-ab-tests/?language=kotlin)
- [Stop A/B test](https://algolia.com/doc/api-reference/api-methods/stop-ab-test/?language=kotlin)
- [Delete A/B test](https://algolia.com/doc/api-reference/api-methods/delete-ab-test/?language=kotlin)




### Insights

- [Clicked Object IDs After Search](https://algolia.com/doc/api-reference/api-methods/clicked-object-ids-after-search/?language=kotlin)
- [Clicked Object IDs](https://algolia.com/doc/api-reference/api-methods/clicked-object-ids/?language=kotlin)
- [Clicked Filters](https://algolia.com/doc/api-reference/api-methods/clicked-filters/?language=kotlin)
- [Converted Objects IDs After Search](https://algolia.com/doc/api-reference/api-methods/converted-object-ids-after-search/?language=kotlin)
- [Converted Object IDs](https://algolia.com/doc/api-reference/api-methods/converted-object-ids/?language=kotlin)
- [Converted Filters](https://algolia.com/doc/api-reference/api-methods/converted-filters/?language=kotlin)
- [Viewed Object IDs](https://algolia.com/doc/api-reference/api-methods/viewed-object-ids/?language=kotlin)
- [Viewed Filters](https://algolia.com/doc/api-reference/api-methods/viewed-filters/?language=kotlin)




### MultiClusters

- [Assign or Move userID](https://algolia.com/doc/api-reference/api-methods/assign-user-id/?language=kotlin)
- [Get top userID](https://algolia.com/doc/api-reference/api-methods/get-top-user-id/?language=kotlin)
- [Get userID](https://algolia.com/doc/api-reference/api-methods/get-user-id/?language=kotlin)
- [List clusters](https://algolia.com/doc/api-reference/api-methods/list-clusters/?language=kotlin)
- [List userIDs](https://algolia.com/doc/api-reference/api-methods/list-user-id/?language=kotlin)
- [Remove userID](https://algolia.com/doc/api-reference/api-methods/remove-user-id/?language=kotlin)
- [Search userID](https://algolia.com/doc/api-reference/api-methods/search-user-id/?language=kotlin)




### Advanced

- [Get logs](https://algolia.com/doc/api-reference/api-methods/get-logs/?language=kotlin)
- [Configuring timeouts](https://algolia.com/doc/api-reference/api-methods/configuring-timeouts/?language=kotlin)
- [Set extra header](https://algolia.com/doc/api-reference/api-methods/set-extra-header/?language=kotlin)
- [Wait for operations](https://algolia.com/doc/api-reference/api-methods/wait-task/?language=kotlin)
