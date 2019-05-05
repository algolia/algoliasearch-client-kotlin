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
