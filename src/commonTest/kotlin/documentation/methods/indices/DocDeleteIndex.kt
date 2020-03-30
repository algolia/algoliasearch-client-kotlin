package documentation.methods.indices

import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocDeleteIndex {

//    suspend fun Index.deleteIndex(
//        requestOptions: __RequestOptions?__ = null
//    ): DeletionIndex

    @Test
    fun snippet1() {
        runBlocking {
            index.deleteIndex()
        }
    }
}
