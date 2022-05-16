package documentation.methods.rule

import documentation.index
import kotlinx.coroutines.test.runTest
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
        runTest {
            index.browseRules().forEach {
                println(it)
            }
        }
    }
}
