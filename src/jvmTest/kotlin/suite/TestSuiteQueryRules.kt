package suite

import com.algolia.search.model.queryrule.QueryRule
import com.algolia.search.model.settings.AttributeForFaceting
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.toAttribute
import io.ktor.client.features.BadResponseStatusException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.list
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual
import shouldNotBeNull


@RunWith(JUnit4::class)
internal class TestSuiteQueryRules {

    private val suffix = "rules"
    private val indexName = testSuiteIndexName(suffix)
    private val brand = "brand".toAttribute()
    private val index = clientAdmin1.getIndex(indexName)

    @Before
    fun clean() {
        cleanIndex(clientAdmin1, suffix)
    }

    @Test
    fun test() {
        runBlocking {
            val objects = load(JsonObjectSerializer.list, "iphones.json")
            val queryRule = load(QueryRule.serializer(), "query_rule_brand.json")
            val queryRules = load(QueryRule.serializer().list, "query_rule_edits.json")
            val tasks = mutableListOf<Task>()

            index.apply {
                tasks += saveObjects(objects)
                tasks += setSettings(Settings(attributesForFaceting = listOf(AttributeForFaceting.Default(brand))))
                tasks += saveRule(queryRule)
                tasks += saveRules(queryRules)

                tasks.wait().all { it is TaskStatus.Published }

                getRule(queryRule.objectID).queryRule shouldEqual queryRule
                queryRules.forEach { getRule(it.objectID).queryRule shouldEqual it }
                val searches = searchRules().hits.map { it.queryRule }

                searches.find { it.objectID == queryRule.objectID }.shouldNotBeNull()
                searches.find { it.objectID == queryRules.first().objectID }.shouldNotBeNull()
                deleteRule(queryRule.objectID).wait() shouldEqual TaskStatus.Published
                var isNotfound = false
                try {
                    getRule(queryRule.objectID)
                } catch (exception: BadResponseStatusException) {
                    isNotfound = exception.statusCode == HttpStatusCode.NotFound
                }
                isNotfound.shouldBeTrue()
                clearRules().wait() shouldEqual TaskStatus.Published
                searchRules().nbHits shouldEqual 0
            }
        }
    }
}