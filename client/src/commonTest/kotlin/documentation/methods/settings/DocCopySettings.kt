package documentation.methods.settings

import documentation.index
import documentation.indexName
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocCopySettings {

//    suspend fun [Index](#method-param-indexnamesrc).copySettings(
//        [destination](#method-param-indexnamedest): __IndexName__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runTest {
            index.copySettings(indexName)
        }
    }
}
