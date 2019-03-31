package documentation.methods.apikey

import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


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