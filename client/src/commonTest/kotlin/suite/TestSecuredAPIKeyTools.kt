package suite

import clientAdmin1
import clientSearch
import com.algolia.search.client.ClientSearch
import com.algolia.search.exception.AlgoliaApiException
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.SecuredAPIKeyRestriction
import com.algolia.search.model.apikey.generateSecuredAPIKey
import com.algolia.search.model.apikey.getSecuredApiKeyRemainingValidity
import com.algolia.search.model.internal.Time
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.internal.Key
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import shouldBeTrue
import shouldEqual
import shouldFailWith
import kotlin.test.Ignore

@Ignore
internal class TestSecuredAPIKeyTools {

    val suffix = "secured_api_keys"
    private val indexName = testSuiteIndexName(suffix)
    private val indexNameDev = indexName.copy(raw = indexName.raw + "_dev")
    private val index = clientAdmin1.initIndex(indexName)
    private val indexDev = clientAdmin1.initIndex(indexNameDev)
    private val restriction = SecuredAPIKeyRestriction(
        restrictIndices = listOf(indexName),
        validUntil = Time.getCurrentTimeMillis() + 10 * 60 * 1000
    )
    private val apiKey = ClientSearch.generateAPIKey(clientSearch.apiKey, restriction)
    private val client = ClientSearch(clientAdmin1.applicationID, apiKey)
    private val data = buildJsonObject { put(Key.ObjectID, "one") }

    fun test() {
        runTest {
            index.apply {
                saveObject(data).wait() shouldEqual TaskStatus.Published
            }
            indexDev.apply {
                saveObject(data).wait() shouldEqual TaskStatus.Published
            }
            client.apply {
                initIndex(indexName).search()
                shouldFailWith<AlgoliaApiException> { initIndex(indexNameDev).search() }
            }
        }
    }

    fun expiredKey() {
        val restrictions = SecuredAPIKeyRestriction(
            validUntil = Time.getCurrentTimeMillis() - 600,
            restrictIndices = listOf(IndexName("index"))
        )
        val key = APIKey("parentKey").generateSecuredAPIKey(restrictions)

        (key.getSecuredApiKeyRemainingValidity() < 0).shouldBeTrue()
    }

    fun validKey() {
        val restrictions = SecuredAPIKeyRestriction(
            validUntil = Time.getCurrentTimeMillis() + 600,
            restrictIndices = listOf(IndexName("index"))
        )
        val key = APIKey("parentKey").generateSecuredAPIKey(restrictions)

        (key.getSecuredApiKeyRemainingValidity() > 0).shouldBeTrue()
    }

    fun remainingValidityParameter() {
        val restrictions = SecuredAPIKeyRestriction(
            restrictIndices = listOf(IndexName("index"))
        )
        val key = APIKey("parentKey").generateSecuredAPIKey(restrictions)

        shouldFailWith<IllegalArgumentException> { key.getSecuredApiKeyRemainingValidity() }
    }
}
