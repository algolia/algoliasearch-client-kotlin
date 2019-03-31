package documentation.reference

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.SortFacetValuesBy
import io.ktor.client.features.ResponseException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.*
import kotlinx.io.IOException
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import runBlocking
import shouldFailWith
import documentation.index
import kotlin.test.Test


@Suppress("UNUSED_VARIABLE", "unused")
internal class DocPhilosophy {

    @Test
    fun unwrappingHits() {
        shouldFailWith<Exception> {
            runBlocking {
                @Serializable
                data class Contact(
                    val firstname: String,
                    val lastname: String
                )

                val response = index.search()

                val contacts: List<Contact> = response.hits.map { it.parse(Contact.serializer()) }
            }
        }
    }

    @Test
    fun unwrappingObject() {
        shouldFailWith<ResponseException> {
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

            val contact: Contact = Json.plain.fromJson(Contact.serializer(), json)
            // Or with Json.nonstrict
            val contactNonStrict: Contact = Json.nonstrict.fromJson(Contact.serializer(), json)
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
    fun strongTypingClient() {
        val appID = ApplicationID("YourApplicationID")
        val apiKey = APIKey("YourAPIKey")

        val client = ClientSearch(appID, apiKey)
    }

    @Test
    fun strongTypingAttributes() {
        val color = Attribute("color")
        val category = Attribute("category")

        Query(
            attributesToRetrieve = listOf(color, category)
        )
    }

    @Test
    fun strongTypingSealedClass() {
        val query = Query()
        val sortFacetValuesBy = query.sortFacetValuesBy

        when (sortFacetValuesBy) {
            is SortFacetValuesBy.Count -> TODO()
            is SortFacetValuesBy.Other -> TODO()
            is SortFacetValuesBy.Alpha -> TODO()
            // No need for an `else ->` branch
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
}