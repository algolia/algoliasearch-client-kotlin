# Getting started

## Init client and index

To start, you need to initialize the client. To do this, you need your **Application ID** and **API Key**.
You can find both on [your Algolia account](https://www.algolia.com/api-keys).

```kotlin
val client = ClientSearch(
    applicationID = ApplicationID("YourApplicationID"),
    apiKey = APIKey("YourAdminAPIKey")
)
val indexName = IndexName("contacts")
val index = client.initIndex(indexName)
```

## Indexing data

Without any prior configuration, you can start indexing [500 contacts](https://github.com/algolia/datasets/blob/master/contacts/contacts.json) in the `contacts` index using the following code:

```kotlin
@Serializable
data class Contact(
    val firstname: String,
    val lastname: String,
    val followers: Int
)

val string = File("contacts.json").readText()
val contacts = Json.decodeFromString(Contact.serializer().list, string)

index.saveObjects(Contact.serializer(), contacts)
```

## Configure index

You can customize settings to fine tune the search behavior. For example, you can add a custom ranking by number of followers to further enhance the built-in relevance:

You can also configure the list of attributes you want to index by order of importance (most important first).

**Note:** Algolia is designed to suggest results as you type, which means you'll generally search by prefix.
In this case, the order of attributes is crucial to decide which hit is the best.

```kotlin
val settings = settings {
    searchableAttributes {
        +"firstname"
        +"lastname"
    }
    customRanking {
        +Desc("followers")
    }
}

index.setSettings(settings)
```

## Search

You can now search for contacts by `firstname`, `lastname`, etc. (even with typos):

```kotlin
val query = Query("jimmie")
val response = index.search(query)

response.hits.deserialize(Contact.serializer())
```
