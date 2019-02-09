package client

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.apikey.ACL
import io.ktor.client.features.BadResponseStatusException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientAPIKey {

    private val indexName = index.indexName
    private val admin = ClientSearch(applicationId, adminKey)
    private val description = "kotlin api key"

    @Test
    fun suite() {
        runBlocking {
            val create = admin.addAPIKey(
                indexes = listOf(indexName),
                rights = listOf(ACL.Search),
                description = description
            )

            admin.deleteAPIKey(create.apiKey)
        }
    }

    @Test
    fun list() {
        runBlocking {
            val response = admin.listAPIKeys()

            response.keys.isNotEmpty().shouldBeTrue()
        }
    }

    @Test
    fun listIndex() {
        runBlocking {
            val response = admin.getIndex(indexName).listIndexAPIKeys()

            response.keys.isNotEmpty().shouldBeTrue()
        }
    }

    @Test
    fun listIndexes() {
        runBlocking {
            val response = admin.listIndexAPIKeys()

            response.keys.isNotEmpty().shouldBeTrue()
        }
    }

    @Test
    fun getPermission() {
        runBlocking {
            val response = admin.getAPIKeyPermission(apiKey)

            response.rights.isNotEmpty().shouldBeTrue()
        }
    }

    @Test
    fun suiteIndex() {
        runBlocking {
            admin.getIndex(indexName).apply {
                val create = addIndexAPIKey(description = description)
                val maxWait = 10000L
                var time = 0L
                val increment = 1000L

                while (time < maxWait) {
                    try {
                        val get = getIndexAPIKey(create.apiKey)

                        get.apiKey shouldEqual create.apiKey
                        break
                    } catch (exception: BadResponseStatusException) {
                        println(exception.localizedMessage)
                    }
                    delay(increment)
                    time += increment
                }

                updateIndexAPIKey(rights = listOf(ACL.Search))
                deleteIndexAPIKey(create.apiKey)
            }
        }
    }
}