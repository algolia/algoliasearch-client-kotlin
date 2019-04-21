# Json serialization

The Kotlin client relies on the kotlinx [serialization](https://github.com/Kotlin/kotlinx.serialization) library.

## Search

Deserialize hits from a search response using the `deserialize` extension functions.

```kotlin
@Serializable
data class Contact(
    val firstname: String,
    val lastname: String
)

val response = index.search()

val contacts: List<Contact> = response.deserialize(Contact.serializer)
```

## GetObject

Deserialize data from a `getObject` call by passing a `serializer` as parameter.

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

## General

A `JsonObject` can be transformed at any moment using the library standard methods.

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
