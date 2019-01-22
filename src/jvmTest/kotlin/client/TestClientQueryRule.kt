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
import shouldBeEmpty
import shouldContain
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientQueryRule {

    private val condition = QueryRule.Condition(
        QueryRule.Pattern.Facet(attributeA),
        QueryRule.Anchoring.Is
    )
    private val consequenceA = QueryRule.Consequence(
        params = Query(query = "query"),
        promote = listOf(QueryRule.Consequence.Promotion(objectIDA, 0)),
        hide = listOf(objectIDB)
    )
    private val consequenceB = QueryRule.Consequence(
        params = Query("blue sky"),
        edits = listOf(QueryRule.Edit("blue"))
    )
    private val queryRuleA = QueryRule(objectIDA, condition, consequenceA)
    private val queryRuleB = QueryRule(objectIDB, condition, consequenceB)

    @Test
    fun save() {
        runBlocking {
            index.apply {
                saveRule(queryRuleA).wait() shouldEqual TaskStatus.Published
                getRule(objectIDA) shouldEqual queryRuleA
                searchRules().hits shouldContain queryRuleA
                deleteRule(objectIDA).wait() shouldEqual TaskStatus.Published
                saveRules(listOf(queryRuleA, queryRuleB)).wait() shouldEqual TaskStatus.Published
                clearRules().wait() shouldEqual TaskStatus.Published
                searchRules().hits.shouldBeEmpty()
            }
        }
    }
}