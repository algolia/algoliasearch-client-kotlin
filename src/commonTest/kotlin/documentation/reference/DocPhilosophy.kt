package documentation.reference

import com.algolia.search.client.ClientSearch
import com.algolia.search.dsl.attributesToRetrieve
import com.algolia.search.dsl.attributesToSnippet
import com.algolia.search.dsl.filters
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.SortFacetsBy
import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.JsonNonStrict
import documentation.client
import documentation.index
import io.ktor.client.features.ResponseException
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.errors.IOException
import kotlin.test.Ignore
import kotlin.test.Test
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import runBlocking

@Suppress("UNUSED_VARIABLE", "unused")
@Ignore
internal class DocPhilosophy {

    @Test
    fun typeSafetyClient() {
        val appID = ApplicationID("YourApplicationID")
        val apiKey = APIKey("YourAdminAPIKey")

        val client = ClientSearch(appID, apiKey)
    }

    @Test
    fun typeSafetyAttributes() {
        val color = Attribute("color")
        val category = Attribute("category")

        Query(
            attributesToRetrieve = listOf(color, category)
        )
    }

    @Test
    fun typeSafety() {
        val query = Query()

        query.sortFacetsBy = SortFacetsBy.Count
//        query.sortFacetsBy = SortFacetsBy.Alpha
//        query.sortFacetsBy = SortFacetsBy.Other("unhandled value")
    }

    @Test
    fun dslQuery() {
        val query = query {
            attributesToRetrieve {
                +"color"
                +"category"
            }
        }
    }

    @Test
    fun dslSettings() {
        val settings = settings {
            attributesToSnippet {
                +"content"(10)
            }
        }
    }

    @Test
    fun dslFilters() {
        val query = query {
            filters {
                and {
                    facet("color", "red")
                    facet("category", "shirt")
                }
                orNumeric {
                    range("price", 0 until 10)
                    comparison("price", Equals, 15)
                }
            }
        }
    }

    @Test
    fun unwrappingHits() {
        runBlocking {
            @Serializable
            data class Contact(
                val firstname: String,
                val lastname: String
            )

            val response = index.search()

            val contacts: List<Contact> = response.hits.map { it.deserialize(Contact.serializer()) }
        }
    }

    @Test
    fun unwrappingObject() {
        runBlocking {
            @Serializable
            data class Contact(
                val firstname: String,
                val lastname: String,
                override val objectID: ObjectID
            ) : Indexable

            val objectID = ObjectID("myID1")

            val contact: Contact = index.getObject(Contact.serializer(), objectID)
        }
    }

    @Test
    fun unwrappingJson() {
        runBlocking {
            @Serializable
            data class Contact(
                val firstname: String,
                val lastname: String
            )

            val json: JsonObject = json {
                "firstname" to "Jimmie"
                "lastname" to "Barninger"
            }

            val contact: Contact = Json.fromJson(Contact.serializer(), json)
            // Or with Json.nonstrict
            val contactNonStrict: Contact = JsonNonStrict.fromJson(Contact.serializer(), json)
        }
    }

    @Test
    fun coroutines() {
        class Searcher : CoroutineScope {

            override val coroutineContext = Job()

            fun search() {
                launch(Dispatchers.Main) {
                    val response = withContext(Dispatchers.Default) { index.search() }
                }
            }
        }
    }

    @Test
    fun exceptionSuccess() {
        runBlocking {
            val response: ResponseSearch = index.search()
        }
    }

    @Test
    fun exceptionHttpFailure() {
        runBlocking {
            try {
                val response = index.search()
            } catch (exception: ResponseException) {
                when (exception.response.status) {
                    HttpStatusCode.NotFound -> TODO()
                    HttpStatusCode.BadRequest -> TODO()
                }
            }
        }
    }

    @Test
    fun exceptionFailure() {
        runBlocking {
            try {
                val response = index.search()
            } catch (exception: ResponseException) {
                TODO()
            } catch (exception: IOException) {
                TODO()
            } catch (exception: Exception) {
                TODO()
            }
        }
    }

    @Test
    fun waitAll() {
        runBlocking {
            index.apply {
                setSettings(Settings()).wait()
            }
            client.run {
                multipleBatchObjects(listOf()).waitAll()
            }
        }
    }
}
