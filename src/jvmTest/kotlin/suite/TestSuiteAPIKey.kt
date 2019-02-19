package suite

import com.algolia.search.model.APIKey
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.search.TypoTolerance
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.toJsonNoDefaults
import com.algolia.search.serialize.urlEncode
import com.algolia.search.toIndexName
import io.ktor.client.features.BadResponseStatusException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSuiteAPIKey {

    lateinit var key: APIKey

    private val rights = listOf(ACL.Search)
    private val description = "A description"
    private val indexes = listOf("index".toIndexName())
    private val maxHitsPerQuery = 1000
    private val maxQueriesPerIPPerHour = 1000
    private val query = Query(typoTolerance = TypoTolerance.Strict)
    private val referers = listOf("referer")

    @Test
    fun test() {
        runBlocking {
            clientAdmin1.apply {
                val response = addAPIKey(
                    rights = rights,
                    description = description,
                    indexes = indexes,
                    maxHitsPerQuery = maxHitsPerQuery,
                    maxQueriesPerIPPerHour = maxQueriesPerIPPerHour,
                    query = query,
                    referers = referers,
                    validity = 600
                )

                key = response.apiKey

                response.wait().let {
                    getAPIKey(key).let {
                        it.apiKey shouldEqual key
                        it.description shouldEqual description
                        it.indexes shouldEqual indexes
                        it.rights shouldEqual rights
                        it.maxHitsPerQuery shouldEqual maxHitsPerQuery
                        it.maxQueriesPerIPPerHour shouldEqual maxQueriesPerIPPerHour
                        it.referers shouldEqual referers
                        it.query shouldEqual query.toJsonNoDefaults().urlEncode()
                    }
                }

                listAPIKeys().keys.any { it.apiKey == key }.shouldBeTrue()

                key = updateAPIKey(key, rights = rights, maxHitsPerQuery = 42).apiKey

                while (isActive) {
                    try {
                        if (getAPIKey(key).maxHitsPerQuery == 42) break
                    } catch (exception: BadResponseStatusException) {
                        exception.statusCode shouldEqual HttpStatusCode.NotFound
                    }
                    delay(1000L)
                }

                deleteAPIKey(key).wait().shouldBeTrue()
            }
        }
    }

    @After
    fun clean() {
        runBlocking {
            clientAdmin1.deleteAPIKey(key)
        }
    }
}