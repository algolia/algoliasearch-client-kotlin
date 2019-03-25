package snippets.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.*
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetSaveRule : TestSnippets() {

//    suspend fun Index.saveRule(
//        [rule](#method-param-queryrule): __Rule__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun saveRule() {
        runBlocking {
            val rule = Rule(
                objectID = ObjectID("myID"),
                enabled = false,
                condition = Condition(
                    pattern = Pattern.Literal("smartphone"),
                    anchoring = Anchoring.Contains
                ),
                consequence = Consequence(
                    params = Params(filters = "category = 1")
                ),
                validity = listOf(TimeRange(0, 10))
            )

            index.saveRule(rule, forwardToReplicas = true)
        }
    }
}