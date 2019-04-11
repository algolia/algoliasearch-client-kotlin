# Philosophy

## Stack

This client provides a Kotlin implentation for using AlgoliaSearch.
This client is built using the official Kotlin stack:
  - [Kotlin multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html).
  - [Kotlinx serialization](https://github.com/Kotlin/kotlinx.serialization) for json parsing.
  - [Kotlinx coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous operations.
  - [Ktor](https://github.com/ktorio/ktor) HTTP client.

## Type safety

Response and parameters objects are typed to provide extensive compile time safety coverage.

Example for creating a Client instance without mixing the application ID and the API key.

```kotlin
val appID = ApplicationID("YourApplicationID")
val apiKey = APIKey("YourAPIKey")

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

## Json serialization

The class `JsonObject` is used by default to represent a record. It is schemaless.
The `JsonObject` class can be transformed to a typed class using the serialization library.

Unwrapping hits from a search response:

```kotlin
@Serializable
data class Contact(
    val firstname: String,
    val lastname: String
)

val response = index.search()

val contacts: List<Contact> = response.hits.map { it.parse(Contact.serializer()) }
```

From a `getObject` call:

```kotlin
@Serializable
data class Contact(
    val firstname: String,
    val lastname: String,
    override val objectID: ObjectID
) : Indexable

val objectID = ObjectID("myID1")

val contact: Contact = index.getObject(Contact.serializer(), objectID)
```

A `JsonObject` can be transformed at any moment using the following syntax:

```kotlin
@Serializable
data class Contact(
    val firstname: String,
    val lastname: String
)

val json: JsonObject = json {
    "firstname" to "Jimmie"
    "lastname" to "Barninger"
}

val contact: Contact = Json.plain.fromJson(Contact.serializer(), json)
// Or with Json.nonstrict, allowing unknown fields to be ignored.
val contactNonStrict: Contact = Json.nonstrict.fromJson(Contact.serializer(), json)
```

Learn more about [kotlinx serialization](https://github.com/Kotlin/kotlinx.serialization).


## Exception handling

In case of success, an HTTP call returns the appropriate typed object.

```kotlin
val response: ResponseSearch = index.search()
```

However, an HTTP exception can occur. Handle it with a try / catch block.

```kotlin
try {
    val response = index.search()
} catch (exception: ResponseException) {
    when (exception.response.status) {
        HttpStatusCode.NotFound -> TODO()
        HttpStatusCode.BadRequest -> TODO()
    }
}
```

Other kinds of exceptions can occur. Handle them appropriately.

```kotlin
try {
    val response = index.search()
} catch (exception: ResponseException) {
    TODO()
} catch (exception: IOException) {
    TODO()
} catch (exception: Exception) {
    TODO()
}
```

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

Use the [apply](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/apply.html) or [run](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/run.html) functions on your index or client:

Example:

```kotlin
index.apply {
    setSettings(Settings()).wait()
}
client.run {
    multipleBatchObjects(listOf<BatchOperationIndex>()).waitAll()
}
```

## Multithreading

The client is designed to be thread-safe. You can use `SearchClient`, `AnalyticsClient`, and `InsightsClient` in a multithreaded environment.
