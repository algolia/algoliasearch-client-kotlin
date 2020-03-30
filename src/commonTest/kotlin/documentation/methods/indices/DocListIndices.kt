package documentation.methods.indices

import documentation.client
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocListIndices {

//    suspend fun ClientSearch.listIndices(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseListIndices

    @Test
    fun snippet1() {
        runBlocking {
            val indices = client.listIndices()

            println(indices)
        }
    }
}
