package documentation.methods.indexing

import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocClearObjects {

//    suspend fun Index.clearObjects(
//        #{requestOptions}: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runBlocking {
            index.clearObjects()
        }
    }
}
