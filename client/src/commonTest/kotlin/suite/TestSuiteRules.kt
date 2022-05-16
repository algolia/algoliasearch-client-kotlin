package suite

import clientAdmin1
import com.algolia.search.exception.AlgoliaApiException
import com.algolia.search.helper.toAttribute
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.search.Query
import com.algolia.search.model.settings.AttributeForFaceting
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonObject
import shouldEqual
import shouldFailWith
import shouldNotBeNull
import kotlin.test.Test

internal class TestSuiteRules {

    private val suffix = "rules"
    private val indexName = testSuiteIndexName(suffix)
    private val brand = "brand".toAttribute()
    private val model = "model".toAttribute()
    private val index = clientAdmin1.initIndex(indexName)

    @Test
    fun test() {
        runTest {
            val objects = load(ListSerializer(JsonObject.serializer()), "iphones.json")
            val rule = load(Rule.serializer(), "rule_brand.json")
            val rules = load(ListSerializer(Rule.serializer()), "rule_batch.json")
            val tasks = mutableListOf<Task>()

            index.apply {
                tasks += saveObjects(objects)
                tasks += setSettings(
                    Settings(
                        attributesForFaceting = listOf(
                            AttributeForFaceting.Default(brand),
                            AttributeForFaceting.Default(model)
                        )
                    )
                )
                tasks += saveRule(rule)
                tasks += saveRules(rules)

                tasks.wait().all { it is TaskStatus.Published }

                getRule(rule.objectID) shouldEqual rule
                rules.forEach { getRule(it.objectID) shouldEqual it }
                val searches = searchRules().hits.map { it.rule }

                searches.find { it.objectID == rule.objectID }.shouldNotBeNull()
                searches.find { it.objectID == rules.first().objectID }.shouldNotBeNull()
                deleteRule(rule.objectID).wait() shouldEqual TaskStatus.Published

                search(Query(ruleContexts = listOf("summer"))).nbHits shouldEqual 1
                (
                    shouldFailWith<AlgoliaApiException> {
                        getRule(rule.objectID)
                    }
                    ).httpErrorCode shouldEqual HttpStatusCode.NotFound.value
                clearRules().wait() shouldEqual TaskStatus.Published
                searchRules().nbHits shouldEqual 0
            }
        }
    }
}
