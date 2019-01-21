package client

import attributeA
import com.algolia.search.saas.data.Query
import com.algolia.search.saas.data.QueryRule
import com.algolia.search.saas.data.TaskStatus
import kotlinx.coroutines.runBlocking
import objectIDA
import objectIDB
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientQueryRule {

    private val condition = QueryRule.Condition(
        QueryRule.Pattern.Facet(attributeA),
        QueryRule.Anchoring.Is
    )
    private val consequence = QueryRule.Consequence(
        params = Query(query = "query"),
        promote = listOf(QueryRule.Consequence.Promotion(objectIDA, 0)),
        hide = listOf(objectIDB)
    )

    @Test
    fun save() {
        runBlocking {
            val queryRule = QueryRule(objectIDA, condition, consequence)

            index.apply {
                saveRule(queryRule).wait() shouldEqual TaskStatus.Published
            }
        }
    }
}