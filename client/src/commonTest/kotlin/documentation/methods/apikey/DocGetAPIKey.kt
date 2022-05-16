package documentation.methods.apikey

import com.algolia.search.model.APIKey
import documentation.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocGetAPIKey {

//    suspend fun ClientSearch.getAPIKey(
//        #{apiKey}: __APIKey__,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseAPIKey

    @Test
    fun snippet1() {
        runTest {
            client.getAPIKey(APIKey("f420238212c54dcfad07ea0aa6d5c45f"))
        }
    }
}
