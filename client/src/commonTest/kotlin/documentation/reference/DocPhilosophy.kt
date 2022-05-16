package documentation.reference

import com.algolia.search.client.ClientSearch
import com.algolia.search.dsl.attributesToRetrieve
import com.algolia.search.dsl.attributesToSnippet
import com.algolia.search.dsl.filters
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.exception.AlgoliaApiException
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.SortFacetsBy
import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.JsonNonStrict
import documentation.client
import documentation.index
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Ignore
import kotlin.test.Test

@Suppress("UNUSED_VARIABLE", "unused")
@Ignore
internal class DocPhilosophy {

    @Serializable
    data class Contact(
        val firstname: String,
        val lastname: String,
        override val objectID: ObjectID
    ) : Indexable

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
        runTest {
            val response = index.search()

            val contacts: List<Contact> = response.hits.map { it.deserialize(Contact.serializer()) }
        }
    }

    @Test
    fun unwrappingObject() {
        runTest {
            val objectID = ObjectID("myID1")

            val contact: Contact = index.getObject(Contact.serializer(), objectID)
        }
    }

    @Test
    fun unwrappingJson() {
        runTest {
            val json: JsonObject = buildJsonObject {
                put("firstname", "Jimmie")
                put("lastname", "Barninger")
            }

            val contact: Contact = Json.decodeFromJsonElement(Contact.serializer(), json)
            // Or with Json.nonstrict
            val contactNonStrict: Contact = JsonNonStrict.decodeFromJsonElement(Contact.serializer(), json)
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
        runTest {
            val response: ResponseSearch = index.search()
        }
    }

    @Test
    fun exceptionHttpFailure() {
        runTest {
            try {
                val response = index.search()
            } catch (exception: AlgoliaApiException) {
                when (exception.httpErrorCode) {
                    404 -> TODO()
                    400 -> TODO()
                }
            }
        }
    }

    @Test
    fun exceptionFailure() {
        runTest {
            try {
                val response = index.search()
            } catch (exception: AlgoliaApiException) {
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
        runTest {
            index.apply {
                setSettings(Settings()).wait()
            }
            client.run {
                multipleBatchObjects(listOf()).waitAll()
            }
        }
    }
}
