package documentation.methods.apikey

import com.algolia.search.model.APIKey
import documentation.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocRestoreAPIKey {

//    suspend fun ClientSearch.restoreAPIKey(
//        #{apiKey}: __APIKey__,
//        requestOptions: __RequestOptions?__ = null
//    ): CreationAPIKey

    @Test
    fun snippet1() {
        runTest {
            client.restoreAPIKey(APIKey("107da8d0afc2d225ff9a7548caaf599f"))
        }
    }
}
