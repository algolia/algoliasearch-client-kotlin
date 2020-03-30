package documentation.methods.indices

import documentation.index
import documentation.indexName
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocMoveIndex {

//    suspend fun [Index](#method-param-src).moveIndex(
//        [destination](#method-param-dest): __IndexName__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runBlocking {
            index.moveIndex(indexName)
        }
    }
}
