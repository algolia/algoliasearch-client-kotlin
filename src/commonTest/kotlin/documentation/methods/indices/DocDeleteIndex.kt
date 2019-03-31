package documentation.methods.indices

import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocDeleteIndex : TestDocumentation() {

//    suspend fun Index.deleteIndex(
//        requestOptions: __RequestOptions?__ = null
//    ): DeletionIndex

    @Test
    fun deleteIndex() {
        runBlocking {
            index.deleteIndex()
        }
    }
}