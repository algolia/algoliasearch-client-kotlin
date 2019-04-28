# Exception handling

## Success

In case of success, an HTTP call returns the appropriate typed object.

```kotlin
val response: ResponseSearch = index.search()
```

## HTTP exceptions

However, an HTTP exception can occur. Handle it with a try / catch block.

```kotlin
try {
    val response = index.search()
} catch (exception: BadResponseStatusException) {
    when (exception.response.status) {
        HttpStatusCode.NotFound -> TODO()
        HttpStatusCode.BadRequest -> TODO()
    }
}
```

## Other exceptions

Other kinds of exceptions can occur. Handle them appropriately.

```kotlin
try {
    val response = index.search()
} catch (exception: BadResponseStatusException) {
    TODO()
} catch (exception: IOException) {
    TODO()
} catch (exception: Exception) {
    TODO()
}
```