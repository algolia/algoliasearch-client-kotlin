package documentation.methods.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.Alternatives
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import com.algolia.search.model.rule.Pattern
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.rule.TimeRange
import com.algolia.search.model.search.Query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocSaveRule {

//    suspend fun Index.saveRule(
//        [rule](#method-param-queryrule): __Rule__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runTest {
            val rule = Rule(
                objectID = ObjectID("a-rule-id"),
                enabled = false,
                conditions = listOf(
                    Condition(
                        pattern = Pattern.Literal("smartphone"),
                        anchoring = Anchoring.Contains,
                        alternative = Alternatives.True
                    )
                ),
                consequence = Consequence(
                    query = Query(filters = "category = 1")
                ),
                validity = listOf(TimeRange(0, 10))
            )

            index.saveRule(rule, forwardToReplicas = true)
        }
    }
}
