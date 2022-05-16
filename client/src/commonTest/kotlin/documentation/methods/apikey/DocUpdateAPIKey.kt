package documentation.methods.apikey

import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.apikey.APIKeyParams
import documentation.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocUpdateAPIKey {

//    suspend fun ClientSearch.updateAPIKey(
//        #{apiKey}: __APIKey__,
//        params: __APIKeyParams__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionAPIKey

    @Test
    fun snippet1() {
        runTest {
            // Update an existing API key that is valid for 300 seconds
            val apiKey = APIKeyParams(
                ACLs = listOf(ACL.Search),
                validity = 300
            )

            client.updateAPIKey(APIKey("myAPIKey"), apiKey)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            // Update an existing index specific API key valid for 300 seconds,
            // with a rate limit of 100 calls per hour per IP and a maximum of 20 hits
            val apiKey = APIKeyParams(
                ACLs = listOf(ACL.Search),
                indices = listOf(IndexName("dev_*")),
                maxHitsPerQuery = 20,
                maxQueriesPerIPPerHour = 100,
                validity = 300
            )

            client.updateAPIKey(APIKey("myAPIKey"), apiKey)
        }
    }
}
