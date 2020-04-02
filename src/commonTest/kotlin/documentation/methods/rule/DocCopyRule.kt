package documentation.methods.rule

import documentation.index
import documentation.indexName
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocCopyRule {

//    suspend fun [Index](#method-param-indexnamesrc).copyRules(
//        [destination](#method-param-indexnamedest): __IndexName__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runBlocking {
            index.copyRules(indexName)
        }
    }
}
