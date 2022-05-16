package suite

import clientAdmin1
import com.algolia.search.exception.AlgoliaApiException
import com.algolia.search.helper.toIndexName
import com.algolia.search.model.APIKey
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.apikey.APIKeyParams
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.TypoTolerance
import com.algolia.search.serialize.internal.toJsonNoDefaults
import com.algolia.search.serialize.internal.urlEncode
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.test.runTest
import shouldBeTrue
import shouldEqual
import kotlin.test.Test

internal class TestSuiteAPIKey {

    private lateinit var key: APIKey

    private val params = APIKeyParams(
        ACLs = listOf(ACL.Search),
        description = "A description",
        indices = listOf("index".toIndexName()),
        maxHitsPerQuery = 1000,
        maxQueriesPerIPPerHour = 1000,
        query = Query(typoTolerance = TypoTolerance.Strict),
        referers = listOf("referer"),
        validity = 600
    )

    @Test
    fun test() {
        runTest {
            clientAdmin1.apply {
                val response = addAPIKey(params)

                key = response.apiKey

                response.wait().let {
                    getAPIKey(key).let {
                        it.apiKey shouldEqual key
                        it.description shouldEqual params.description
                        it.indices shouldEqual params.indices
                        it.ACLs shouldEqual params.ACLs
                        it.maxHitsPerQuery shouldEqual params.maxHitsPerQuery
                        it.maxQueriesPerIPPerHour shouldEqual params.maxQueriesPerIPPerHour
                        it.referers shouldEqual params.referers
                        it.query shouldEqual params.query?.toJsonNoDefaults()?.urlEncode()
                    }
                }

                listAPIKeys().keys.any { it.apiKey == key }.shouldBeTrue()

                key = updateAPIKey(key, params.copy(maxHitsPerQuery = 42)).apiKey

                while (isActive) {
                    try {
                        if (getAPIKey(key).maxHitsPerQuery == 42) break
                    } catch (exception: AlgoliaApiException) {
                        exception.httpErrorCode shouldEqual HttpStatusCode.NotFound
                    }
                    delay(1000L)
                }
                deleteAPIKey(key).wait().shouldBeTrue()
                restoreAPIKey(key).wait()
                deleteAPIKey(key).wait().shouldBeTrue()
            }
        }
    }
}
