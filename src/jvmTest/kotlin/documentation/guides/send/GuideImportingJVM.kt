package documentation.guides.send

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.json.json
import kotlinx.serialization.list
import org.junit.Ignore
import org.junit.Test
import java.io.File


@Suppress("UNUSED_VARIABLE")
@Ignore
internal class GuideImportingJVM {

    @Serializable
    data class Actor(
        val name: String,
        val rating: Int,
        val imagePath: String,
        val alternativePath: String,
        override val objectID: ObjectID
    ) : Indexable

    @Test
    fun batching() {
        runBlocking {
            val client = ClientSearch(ApplicationID("YourApplicationID"), APIKey("YourAdminAPIKey"))
            val index = client.initIndex(IndexName("actors"))
            val string = File("actors.json").readText()
            val actors = Json.plain.parse(JsonObjectSerializer.list, string)

            index.apply {
                actors
                    .chunked(1000)
                    .map { saveObjects(it) }
                    .wait() // Wait for all indexing operations to complete.
            }
        }
    }

    @Test
    fun readFile() {
        @Serializable
        data class Actor(
            val name: String,
            val rating: Int,
            val imagePath: String,
            val alternativePath: String,
            override val objectID: ObjectID
        ) : Indexable

        val string = File("actors.json").readText()
        val actors: List<Actor> = Json.plain.parse(Actor.serializer().list, string)
    }

    @Test
    fun fetchFromDatabase() {
        fun fetchFromDatabase(): List<Actor> {
            val actors: List<Actor> = listOf() // Fetch data from your database

            return actors
        }

        val actors: List<Actor> = fetchFromDatabase()
    }

    @Test
    fun fetchDataFromSource() {
        val records = listOf(
            json { "name" to "Tom Cruise" },
            json { "name" to "Scarlett Johansson" }
        )
    }
}