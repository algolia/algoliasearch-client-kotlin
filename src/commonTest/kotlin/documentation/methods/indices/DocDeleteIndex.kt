package documentation.methods.indices

import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

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
