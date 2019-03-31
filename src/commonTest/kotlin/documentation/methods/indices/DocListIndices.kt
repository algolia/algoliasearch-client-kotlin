package documentation.methods.indices

import runBlocking
import documentation.client
import kotlin.test.Test


internal class DocListIndices {

//    suspend fun ClientSearch.listIndices(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseListIndices

    @Test
    fun listIndices() {
        runBlocking {
            val indices = client.listIndices()

            println(indices)
        }
    }
}