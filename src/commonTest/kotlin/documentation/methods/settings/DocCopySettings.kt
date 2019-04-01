package documentation.methods.settings

import documentation.index
import indexName
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocCopySettings {

//    suspend fun [Index](#method-param-indexnamesrc).copySettings(
//        [destination](#method-param-indexnamedest): __IndexName__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun copySettings() {
        runBlocking {
            index.copySettings(indexName)
        }
    }
}