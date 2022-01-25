package documentation.tutorials

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.serialize.internal.Json
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonObject
import org.junit.Ignore
import org.junit.Test

@Ignore
internal class TutorialPushData {

//    // In your build.gradle
//    dependencies {
//        implementation "com.algolia:algoliasearch-client-kotlin-jvm:$kotlin_client_version"
//    }

    @Test
    fun ecommerce() {
        runBlocking {
            val content = HttpClient().get("https://alg.li/doc-ecommerce.json").bodyAsText()
            val objects = Json.decodeFromString(ListSerializer(JsonObject.serializer()), content)
            val client = ClientSearch(
                ApplicationID("YourApplicationID"),
                APIKey("YourAdminAPIKey")
            )

            val index = client.initIndex(IndexName("demo_ecommerce"))
            index.saveObjects(objects)
        }
    }

    @Test
    fun saas() {
        runBlocking {
            val content = HttpClient().get("https://alg.li/doc-saas.json").bodyAsText()
            val objects = Json.decodeFromString(ListSerializer(JsonObject.serializer()), content)
            val client = ClientSearch(
                ApplicationID("YourApplicationID"),
                APIKey("YourAdminAPIKey")
            )

            val index = client.initIndex(IndexName("demo_saas"))
            index.saveObjects(objects)
        }
    }

    @Test
    fun media() {
        runBlocking {
            val content = HttpClient().get("https://alg.li/doc-media.json").bodyAsText()
            val objects = Json.decodeFromString(ListSerializer(JsonObject.serializer()), content)
            val client = ClientSearch(
                ApplicationID("YourApplicationID"),
                APIKey("YourAdminAPIKey")
            )

            val index = client.initIndex(IndexName("demo_media"))
            index.saveObjects(objects)
        }
    }

    @Test
    fun geo() {
        runBlocking {
            val content = HttpClient().get("https://alg.li/doc-geo.json").bodyAsText()
            val objects = Json.decodeFromString(ListSerializer(JsonObject.serializer()), content)
            val client = ClientSearch(
                ApplicationID("YourApplicationID"),
                APIKey("YourAdminAPIKey")
            )

            val index = client.initIndex(IndexName("demo_geo"))
            index.saveObjects(objects)
        }
    }
}
