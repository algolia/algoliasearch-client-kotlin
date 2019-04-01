package documentation.methods.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.*
import com.algolia.search.model.search.Query
import documentation.index
import runBlocking
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
                    query = Query(filters = "category = 1")
                ),
                validity = listOf(TimeRange(0, 10))
            )

            index.saveRule(rule, forwardToReplicas = true)
        }
    }
}