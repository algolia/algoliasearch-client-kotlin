package suite

import clientAdmin1
import clientSearch
import com.algolia.search.helper.toAttribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.search.Query
import com.algolia.search.model.settings.AttributeForFaceting
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.list
import runBlocking
import shouldBeNull
import shouldBeTrue
import shouldContain
import shouldEqual
import shouldNotBeEmpty
import shouldNotBeNull
import kotlin.test.AfterTest
import kotlin.test.Test


internal class TestSuiteSearch {

    private val suffix = "search"
    private val indexName = testSuiteIndexName(suffix)
    private val company = "company".toAttribute()
    private val allFacets = setOf("*".toAttribute())
    private val index = clientAdmin1.initIndex(indexName)
    private val search = clientSearch.initIndex(indexName)

    @AfterTest
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

                search(Query("algolia")).apply {
                    nbHits shouldEqual 2
                    getObjectIDPosition(ObjectID("nicolas-dessaigne")) shouldEqual 0
                    getObjectIDPosition(ObjectID("julien-lemoine")) shouldEqual 1
                    getObjectIDPosition(ObjectID("unknown")) shouldEqual -1
                }

                findFirstObject({ false }, Query(""), false).shouldBeNull()
                findFirstObject({ true }, Query(""), false)!!.apply {
                    page shouldEqual 0
                    position shouldEqual 0
                }
                val predicate = { hit: ResponseSearch.Hit -> hit.json.getPrimitive("company").content == "Apple" }

                findFirstObject(predicate, Query("Algolia"), false).shouldBeNull()
                findFirstObject(predicate, Query(hitsPerPage = 5), true).shouldBeNull()
                findFirstObject(predicate, Query(hitsPerPage = 5), false)!!.apply {
                    position shouldEqual 0
                    page shouldEqual 2
                }

               search(Query("elon", clickAnalytics = true)).apply {
                   hits.shouldNotBeNull()
                   hits.shouldNotBeEmpty()
               }
            }
            search.apply {
                search(Query(facets = allFacets, filters = "company:tesla")).nbHits shouldEqual 1
                search(Query(facets = allFacets, filters = "(company:tesla OR company:spacex)")).nbHits shouldEqual 2
                val response = searchForFacets(company, "a")
                val facetHits = response.facets.map { it.value }

                facetHits shouldContain "Algolia"
                facetHits shouldContain "Amazon"
                facetHits shouldContain "Apple"
                facetHits shouldContain "Arista Networks"
            }
        }
    }
}