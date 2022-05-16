package documentation.methods.rule

import com.algolia.search.model.rule.Rule
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocBatchRules {

//    suspend fun Index.saveRules(
//        #{rules}: __List<Rule>__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        #{clearExistingRules}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runTest {
            val rules = listOf<Rule>()

            index.saveRules(rules, forwardToReplicas = true, clearExistingRules = true)
        }
    }
}
