package snippets.apikey

import com.algolia.search.model.APIKey
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetRestoreAPIKey : TestSnippets() {

//    suspend fun ClientSearch.restoreAPIKey(
//        #{apiKey}: __APIKey__,
//        requestOptions: __RequestOptions?__ = null
//    ): CreationAPIKey

    @Test
    fun restoreAPIKey() {
        shouldFailWith<ResponseException> {
            runBlocking {
                client.restoreAPIKey(APIKey("107da8d0afc2d225ff9a7548caaf599f"))
            }
        }
    }
}