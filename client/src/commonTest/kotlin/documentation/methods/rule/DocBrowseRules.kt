package documentation.methods.rule

import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocBrowseRules {

//    suspend fun Index.browseRules(
//        query: __RuleQuery__ = RuleQuery(),
//        requestOptions: __RequestOptions?__ = null
//    ): List<ResponseSearchRules>

    @Test
    fun snippet1() {
        runBlocking {
            index.browseRules().forEach {
                println(it)
            }
        }
    }
}
