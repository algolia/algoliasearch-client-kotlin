package documentation.methods.rule

import com.algolia.search.model.rule.Rule
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocReplaceRules {

//    suspend fun Index.replaceAllRules(
//        #{rules}: __List<Rule>__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        // Fetch your rules
        runTest {
            val rules = listOf<Rule>()

            index.replaceAllRules(rules, forwardToReplicas = true)
        }
    }
}
