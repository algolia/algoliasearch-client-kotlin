package documentation.methods.apikey

import com.algolia.search.model.APIKey
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocGetAPIKey : TestDocumentation() {

//    suspend fun ClientSearch.getAPIKey(
//        #{apiKey}: __APIKey__,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseAPIKey

    @Test
    fun getAPIKey() {
        shouldFailWith<ResponseException> {
            runBlocking {
                client.getAPIKey(APIKey("f420238212c54dcfad07ea0aa6d5c45f"))
            }
        }
    }
}