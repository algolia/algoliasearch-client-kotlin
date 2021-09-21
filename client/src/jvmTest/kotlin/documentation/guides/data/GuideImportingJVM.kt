package documentation.guides.data

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.serialize.internal.Json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Ignore
import org.junit.Test
import java.io.File

@Suppress("UNUSED_VARIABLE", "UnnecessaryVariable")
@Ignore
internal class GuideImportingJVM {
    @Test
    fun snippet1() {
        runBlocking {
            val client = ClientSearch(ApplicationID("YourApplicationID"), APIKey("YourAdminAPIKey"))
            val index = client.initIndex(IndexName("actors"))
            val string = File("actors.json").readText()
            val actors = Json.decodeFromString(ListSerializer(JsonObject.serializer()), string)

            index.apply {
                actors
                    .chunked(1000)
                    .map { saveObjects(it) }
                    .wait() // Wait for all indexing operations to complete.
            }
        }
    }

    @Test
    fun snippet2() {
        val string = File("actors.json").readText()
        val actors: List<Actor> = Json.decodeFromString(ListSerializer(Actor.serializer()), string)
    }

    @Test
    fun snippet3() {
        fun fetchFromDatabase(): List<Actor> {
            val actors: List<Actor> = listOf() // Fetch data from your database

            return actors
        }

        val actors: List<Actor> = fetchFromDatabase()
    }

    @Test
    fun snippet4() {
        val records = listOf(
            buildJsonObject { put("name", "Tom Cruise") },
            buildJsonObject { put("name", "Scarlett Johansson") }
        )
    }
}

@Serializable
data class Actor(
    val name: String,
    val rating: Int,
    val imagePath: String,
    val alternativePath: String,
    override val objectID: ObjectID,
) : Indexable
