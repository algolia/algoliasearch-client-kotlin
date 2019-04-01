package documentation.methods.apikey

import documentation.TestDocumentation
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocListAPIKey : TestDocumentation() {

//    suspend fun ClientSearch.listAPIKeys(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseListAPIKey

    @Test
    fun listAPIKey() {
        runBlocking {
            client.listAPIKeys()
        }
    }
}