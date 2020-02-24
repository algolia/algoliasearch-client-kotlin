package suite

import clientAdmin1
import clientSearch
import com.algolia.search.dsl.*
import com.algolia.search.helper.readContent
import com.algolia.search.helper.toAttribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.search.ExplainModule
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Query
import com.algolia.search.model.settings.AttributeForFaceting
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import io.ktor.client.features.ResponseException
import kotlinx.serialization.Serializable
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
                    getObjectPosition(ObjectID("nicolas-dessaigne")) shouldEqual 0
                    getObjectPosition(ObjectID("julien-lemoine")) shouldEqual 1
                    getObjectPosition(ObjectID("unknown")) shouldEqual -1
                }

                findObject({ false }, Query(""), paginate = true).shouldBeNull()
                findObject({ true }, Query(""), paginate = true)!!.apply {
                    page shouldEqual 0
                    position shouldEqual 0
                }
                val predicate = { hit: ResponseSearch.Hit -> hit.json.getPrimitive("company").content == "Apple" }

                findObject(predicate, Query("Algolia"), paginate = true).shouldBeNull()
                findObject(predicate, Query(hitsPerPage = 5), paginate = false).shouldBeNull()
                findObject(predicate, Query(hitsPerPage = 5), paginate = true)!!.apply {
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

    @Test
    fun explain() {

        @Serializable
        data class DecompoundingObject(
            override val objectID: ObjectID,
            val type: String,
            val category: String? = null
        ): Indexable

        runBlocking {
            val settings = settings {
                decompoundedAttributes {
                    german {
                        +"category"
                        +"type"
                        +"desc"
                    }
                }
                queryLanguages {
                    +German
                }
                ignorePlurals = IgnorePlurals.True
            }
            val data = listOf(
                DecompoundingObject(ObjectID("A...B"), "Hütte", "Hunde"),
                DecompoundingObject(ObjectID("AB"), "Ich will ein schöne Hundehütte"),
                DecompoundingObject(ObjectID("BA"), "Hüttehunde, Es ist eine lustige Verbindung!"),
                DecompoundingObject(ObjectID("B...A"), "Eine Hütte für Hunde"),
                DecompoundingObject(ObjectID("A"), "Hunde"),
                DecompoundingObject(ObjectID("B"), "Hütte")
            )

            index.apply {
                saveObjects(DecompoundingObject.serializer(), data).wait()
                setSettings(settings).wait()
            }

            val search = index.search(
                query("Hundehütte") {
                    ignorePlurals = IgnorePlurals.True
                    explainModules { +ExplainModule.MatchAlternatives }
                }
            )

            search.explainOrNull.shouldNotBeNull()
            search.explain.match.alternatives.size shouldEqual 13
        }
    }
}