package documentation.methods.settings

import documentation.index
import documentation.indexName
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocCopySettings {

//    suspend fun [Index](#method-param-indexnamesrc).copySettings(
//        [destination](#method-param-indexnamedest): __IndexName__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runBlocking {
            index.copySettings(indexName)
        }
    }
}
