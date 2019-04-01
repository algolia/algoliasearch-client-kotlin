package documentation.methods.rule

import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocBrowseRules {

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