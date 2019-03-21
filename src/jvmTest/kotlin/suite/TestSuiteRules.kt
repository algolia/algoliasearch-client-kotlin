package suite

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.settings.AttributeForFaceting
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import io.ktor.client.features.ResponseException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.list
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import shouldFailWith
import shouldNotBeNull


@RunWith(JUnit4::class)
internal class TestSuiteRules {

    private val suffix = "rules"
    private val indexName = testSuiteIndexName(suffix)
    private val brand = "brand".toAttribute()
    private val index = clientAdmin1.initIndex(indexName)

    @Before
    fun clean() {
        runBlocking {
            cleanIndex(clientAdmin1, suffix)
        }
    }

    @Test
    fun test() {
        runBlocking {
            val objects = load(JsonObjectSerializer.list, "iphones.json")
            val rule = load(Rule.serializer(), "rule_brand.json")
            val rules = load(Rule.serializer().list, "rule_edits.json")
            val tasks = mutableListOf<Task>()

            index.apply {
                tasks += saveObjects(objects)
                tasks += setSettings(Settings(attributesForFaceting = listOf(AttributeForFaceting.Default(brand))))
                tasks += saveRule(rule)
                tasks += saveRules(rules)

                tasks.wait().all { it is TaskStatus.Published }

                getRule(rule.objectID).rule shouldEqual rule
                rules.forEach { getRule(it.objectID).rule shouldEqual it }
                val searches = searchRules().hits.map { it.rule }

                searches.find { it.objectID == rule.objectID }.shouldNotBeNull()
                searches.find { it.objectID == rules.first().objectID }.shouldNotBeNull()
                deleteRule(rule.objectID).wait() shouldEqual TaskStatus.Published

                (ResponseException::class shouldFailWith {
                    getRule(rule.objectID)
                }).response.status.value shouldEqual HttpStatusCode.NotFound.value
                clearRules().wait() shouldEqual TaskStatus.Published
                searchRules().nbHits shouldEqual 0
            }
        }
    }
}