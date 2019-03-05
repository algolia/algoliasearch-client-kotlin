package suite

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.apikey.SecuredAPIKeyRestriction
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyObjectID
import io.ktor.client.features.BadResponseStatusException
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import shouldFailWith
import java.util.*
import java.util.concurrent.TimeUnit


@RunWith(JUnit4::class)
internal class TestSecuredAPIKey {

    private val suffix = "secured_api_keys"
    private val indexName = testSuiteIndexName(suffix)
    private val indexNameDev = indexName.copy(raw = indexName.raw + "_dev")
    private val index = clientAdmin1.initIndex(indexName)
    private val indexDev = clientAdmin1.initIndex(indexNameDev)
    private val restriction = SecuredAPIKeyRestriction(
        restrictIndices = listOf(indexName),
        validUntil = Date(Date().time + TimeUnit.MILLISECONDS.convert(10, TimeUnit.MINUTES)).time
    )
    private val apiKey = ClientSearch.generateAPIKey(clientSearch.apiKey, restriction)
    private val client = ClientSearch(clientAdmin1.applicationID, apiKey)
    private val data = json { KeyObjectID to "one" }

    @Before
    fun clean() {
        runBlocking {
            cleanIndex(clientAdmin1, suffix)
        }
    }

    @Test
    fun test() {
        runBlocking {
            index.apply {
                saveObject(data).wait() shouldEqual TaskStatus.Published
            }
            indexDev.apply {
                saveObject(data).wait() shouldEqual TaskStatus.Published
            }
            client.apply {
                initIndex(indexName).search()
                BadResponseStatusException::class shouldFailWith { initIndex(indexNameDev).search() }
            }
        }
    }
}