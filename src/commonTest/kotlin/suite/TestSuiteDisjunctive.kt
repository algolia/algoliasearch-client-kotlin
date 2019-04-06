package suite

import clientAdmin1
import com.algolia.search.helper.toAttribute
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.search.FacetStats
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.get
import com.algolia.search.model.settings.AttributeForFaceting
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.list
import runBlocking
import shouldBeTrue
import shouldEqual
import shouldNotBeNull
import kotlin.test.BeforeTest
import kotlin.test.Test


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
            val facets = listOf(brand, category, stars)
            val settings = Settings(
                attributesForFaceting = facets.map { AttributeForFaceting.Default(it) }
            )
            val objects = load(JsonObjectSerializer.list, "disjunctive_A.json")
            val query = Query(
                query = "phone",
                facets = facets
            )
            val disjunctiveFacets = listOf(brand)
            val filters = listOf(
                Filter.Facet(brand, "Apple"),
                Filter.Facet(brand, "Samsung"),
                Filter.Facet(brand, "Commas' voice, Ltd"),
                Filter.Facet(category, "device")
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
                it.facetStats.size shouldEqual 1
                it.facetStats[stars] shouldEqual FacetStats(2f, 5f, 4f, 16f)
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
            val filters = mutableListOf<Filter.Facet>()

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
                it.facetStats.size shouldEqual 1
                it.facetStats[stars] shouldEqual FacetStats(1f, 4f, 2f, 12f)
            }
            filters += Filter.Facet(stars, "1")
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
                it.facetStats.size shouldEqual 1
                it.facetStats[stars] shouldEqual FacetStats(1f, 4f, 2f, 12f)
            }
            filters += Filter.Facet(city, "Paris")
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
                it.facetStats.size shouldEqual 1
                it.facetStats[stars] shouldEqual FacetStats(1f, 4f, 2f, 6f)
            }
            filters += Filter.Facet(stars, "4")
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
                it.facetStats.size shouldEqual 1
                it.facetStats[stars] shouldEqual FacetStats(1f, 4f, 2f, 6f)
            }
        }
    }
}