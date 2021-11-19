package documentation.methods.indices

import documentation.index
import runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocDeleteIndex {

//    suspend fun Index.deleteIndex(
//        requestOptions: __RequestOptions?__ = null
//    ): DeletionIndex

    @Test
    fun snippet1() {
        runTest {
            index.deleteIndex()
        }
    }
}
