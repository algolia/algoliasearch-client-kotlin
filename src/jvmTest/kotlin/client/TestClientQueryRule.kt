package client

import attributeA
import com.algolia.search.saas.model.common.TaskStatus
import com.algolia.search.saas.model.query_rule.*
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

    private val condition = Condition(
        Pattern.Facet(attributeA),
        Anchoring.Is
    )
    private val consequenceA = Consequence(
        params = Params(
            query = QueryOrEdits.Query(
                "query"
            )
        ),
        promote = listOf(Promotion(objectIDA, 0)),
        hide = listOf(objectIDB)
    )
    private val consequenceB = Consequence(
        params = Params(
            query = QueryOrEdits.Edits(
                listOf(
                    Edit("blue")
                )
            )
        )
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
                searchRules().hits shouldEqual listOf(queryRuleA, queryRuleB)
                clearRules().wait() shouldEqual TaskStatus.Published
                searchRules().hits.shouldBeEmpty()
            }
        }
    }
}