package documentation.methods.indexing

import documentation.TestDocumentation
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocClearObjects : TestDocumentation() {

//    suspend fun Index.clearObjects(
//        #{requestOptions}: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun deleteObjectBy() {
        runBlocking {
            index.clearObjects()
        }
    }
}