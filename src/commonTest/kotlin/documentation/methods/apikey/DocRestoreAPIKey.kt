package documentation.methods.apikey

import com.algolia.search.model.APIKey
import documentation.client
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocRestoreAPIKey {

//    suspend fun ClientSearch.restoreAPIKey(
//        #{apiKey}: __APIKey__,
//        requestOptions: __RequestOptions?__ = null
//    ): CreationAPIKey

    @Test
    fun snippet1() {
        runBlocking {
            client.restoreAPIKey(APIKey("107da8d0afc2d225ff9a7548caaf599f"))
        }
    }
}
