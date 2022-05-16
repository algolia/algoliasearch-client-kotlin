package documentation.methods.indices

import documentation.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocListIndices {

//    suspend fun ClientSearch.listIndices(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseListIndices

    @Test
    fun snippet1() {
        runTest {
            val indices = client.listIndices()

            println(indices)
        }
    }
}
