package documentation.methods.indices

import documentation.client
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
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