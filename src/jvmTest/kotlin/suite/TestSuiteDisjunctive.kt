package suite

import com.algolia.search.filter.FilterFacet
import com.algolia.search.helper.get
import com.algolia.search.helper.toAttribute
import com.algolia.search.model.search.Query
import com.algolia.search.model.settings.AttributeForFaceting
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.list
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual
import shouldNotBeNull
import kotlin.test.BeforeTest


@RunWith(JUnit4::class)
internal class TestSuiteDisjunctive {

    private val suffix = "disjunctive"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @BeforeTest
    fun clean() {
        runBlocking {
            cleanIndex(clientAdmin1, suffix)
        }
    }

    @Test
    fun testA() {
        runBlocking {
            val brand = "brand".toAttribute()
            val category = "category".toAttribute()
            val stars = "stars".toAttribute()
            val settings = Settings(
                attributesForFaceting = listOf(brand, category).map { AttributeForFaceting.Default(it) }
            )
            val objects = load(JsonObjectSerializer.list, "disjunctive_A.json")
            val query = Query(
                query = "phone",
                facets = listOf(brand, category, stars)
            )
            val disjunctiveFacets = listOf(brand)
            val filters = listOf(
                FilterFacet(brand, "Apple"),
                FilterFacet(brand, "Samsung"),
                FilterFacet(brand, "Commas' voice, Ltd"),
                FilterFacet(category, "device")
            )

            index.apply {
                val tasks = mutableListOf<Task>()

                tasks += saveObjects(objects)
                tasks += setSettings(settings)

                tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()
            }
            index.searchDisjunctiveFacets(query, disjunctiveFacets, filters).let {
                it.nbHits shouldEqual 4
                it.disjunctiveFacetsOrNull.shouldNotBeNull()
                it.disjunctiveFacets[brand, "Apple"] shouldEqual 2
                it.disjunctiveFacets[brand, "Samsung"] shouldEqual 1
                it.disjunctiveFacets[brand, "Whatever"] shouldEqual 1
                it.disjunctiveFacets[brand, "Commas' voice, Ltd"] shouldEqual 1
                it.exhaustiveFacetsCount.shouldBeTrue()
            }
        }
    }

    @Test
    fun testB() {
        runBlocking {
            val city = "city".toAttribute()
            val stars = "stars".toAttribute()
            val facilities = "facilities".toAttribute()
            val settings = Settings(
                attributesForFaceting = listOf(stars, city, facilities).map { AttributeForFaceting.Default(it) }
            )
            val objects = load(JsonObjectSerializer.list, "disjunctive_B.json")
            val query = Query(
                query = "h",
                facets = listOf(city)
            )
            val disjunctiveFacets = listOf(stars, facilities)
            val filters = mutableListOf<FilterFacet>()

            index.apply {
                val tasks = mutableListOf<Task>()

                tasks += saveObjects(objects)
                tasks += setSettings(settings)

                tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()
            }
            index.searchDisjunctiveFacets(query, disjunctiveFacets, filters).let {
                it.nbHits shouldEqual 5
                it.facets.size shouldEqual 1
                it.disjunctiveFacets.size shouldEqual 2
                it.exhaustiveFacetsCount.shouldBeTrue()
                it.disjunctiveFacets.apply {
                    this[stars, "1"] shouldEqual 2
                    this[stars, "2"] shouldEqual 1
                    this[stars, "4"] shouldEqual 2
                }
                it.disjunctiveFacets.apply {
                    this[facilities, "spa"] shouldEqual 3
                    this[facilities, "bath"] shouldEqual 2
                    this[facilities, "wifi"] shouldEqual 2
                }
            }
            filters += FilterFacet(stars, "1")
            index.searchDisjunctiveFacets(query, disjunctiveFacets, filters).let {
                it.nbHits shouldEqual 2
                it.facets.size shouldEqual 1
                it.disjunctiveFacets.size shouldEqual 2
                it.disjunctiveFacets.apply {
                    this[stars, "1"] shouldEqual 2
                    this[stars, "2"] shouldEqual 1
                    this[stars, "4"] shouldEqual 2
                }
                it.disjunctiveFacets.apply {
                    this[facilities, "spa"] shouldEqual 1
                    this[facilities, "bath"] shouldEqual 1
                    this[facilities, "wifi"] shouldEqual 2
                }
                it.exhaustiveFacetsCount.shouldBeTrue()
            }
            filters += FilterFacet(city, "Paris")
            index.searchDisjunctiveFacets(query, disjunctiveFacets, filters).let {
                it.nbHits shouldEqual 2
                it.facets.size shouldEqual 1
                it.disjunctiveFacets.size shouldEqual 2
                it.disjunctiveFacets.apply {
                    this[stars, "1"] shouldEqual 2
                    this[stars, "4"] shouldEqual 1
                }
                it.disjunctiveFacets.apply {
                    this[facilities, "spa"] shouldEqual 1
                    this[facilities, "bath"] shouldEqual 1
                    this[facilities, "wifi"] shouldEqual 2
                }
            }
            filters += FilterFacet(stars, "4")
            index.searchDisjunctiveFacets(query, disjunctiveFacets, filters).let {
                it.nbHits shouldEqual 3
                it.facets.size shouldEqual 1
                it.disjunctiveFacets.size shouldEqual 2
                it.disjunctiveFacets.apply {
                    this[stars, "1"] shouldEqual 2
                    this[stars, "4"] shouldEqual 1
                }
                it.disjunctiveFacets.apply {
                    this[facilities, "spa"] shouldEqual 2
                    this[facilities, "bath"] shouldEqual 1
                    this[facilities, "wifi"] shouldEqual 2
                }
            }
        }
    }
}