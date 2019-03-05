package suite

import com.algolia.search.model.search.Query
import com.algolia.search.model.settings.AttributeForFaceting
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.toAttribute
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.list
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldContain
import shouldEqual
import shouldNotBeEmpty
import shouldNotBeNull


@RunWith(JUnit4::class)
internal class TestSuiteSearch {

    private val suffix = "search"
    private val indexName = testSuiteIndexName(suffix)
    private val company = "company".toAttribute()
    private val allFacets = listOf("*".toAttribute())
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
            val objects = load(JsonObjectSerializer.list, "companies.json")
            val settings = Settings(attributesForFaceting = listOf(AttributeForFaceting.Searchable(company)))
            val tasks = mutableListOf<Task>()

            index.apply {
                tasks += setSettings(settings)
                tasks += saveObjects(objects)

                tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()
                search(Query("algolia")).nbHits shouldEqual 2
                val hits = search(Query("elon", clickAnalytics = true)).hits

                hits.shouldNotBeNull()
                hits.shouldNotBeEmpty()

                search(Query(facets = allFacets, filters = "company:tesla")).nbHits shouldEqual 1
                search(Query(facets = allFacets, filters = "(company:tesla OR company:spacex)")).nbHits shouldEqual 2
                val facetHits = searchForFacetValue(company, "a").facetHits.map { it.value }

                facetHits shouldContain "Algolia"
                facetHits shouldContain "Amazon"
                facetHits shouldContain "Apple"
                facetHits shouldContain "Arista Networks"
            }
        }
    }
}