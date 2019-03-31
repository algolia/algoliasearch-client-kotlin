package documentation.methods.rule

import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocBrowseRules : TestDocumentation() {

//    suspend fun Index.browseRules(
//        query: __RuleQuery__ = RuleQuery(),
//        requestOptions: __RequestOptions?__ = null,
//        block: __suspend (ResponseRules) -> Unit__
//    )

    @Test
    fun browseRules() {
        runBlocking {
            index.browseRules {
                println(it)
            }
        }
    }
}