package suite
import com.algolia.search.browseAllObjects
import com.algolia.search.browseAllRules
import com.algolia.search.browseAllSynonyms
import com.algolia.search.model.rule.QueryRule
import com.algolia.search.model.search.Query
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.toObjectID
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSuiteBrowseAll {

    private val suffix = "helper"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.getIndex(indexName)

    @Before
    fun clean() {
        cleanIndex(clientAdmin1, suffix)
    }

    @Test
    fun rules() {
        runBlocking {
            val ruleA = load(QueryRule.serializer(), "query_rule_brand.json")
            val ruleB = load(QueryRule.serializer(), "query_rule_company.json")
            var count = 0

            index.apply {
                saveRules(listOf(ruleA, ruleB)).wait() shouldEqual TaskStatus.Published

                browseAllRules(hitsPerPage = 1) { page ->
                    nbHits shouldEqual 2
                    hits.size shouldEqual 1
                    page shouldEqual count
                    count++
                }
                count shouldEqual 2
            }
        }
    }

    @Test
    fun synonyms() {
        runBlocking {
            val synonymA = Synonym.OneWay("a".toObjectID(), "a", listOf("b"))
            val synonymB = Synonym.Placeholder("b".toObjectID(), Synonym.Placeholder.Token("as"), listOf("sad"))
            var count = 0

            index.apply {
                saveSynonyms(listOf(synonymA, synonymB)).wait() shouldEqual TaskStatus.Published

                browseAllSynonyms(hitsPerPage = 1) { page ->
                    nbHits shouldEqual 2
                    hits.size shouldEqual 1
                    page shouldEqual count
                    count++
                }
                count shouldEqual 2
            }
        }
    }

    @Test
    fun objects() {
        runBlocking {
            val objects = (0 until 10).map { json { } }

            index.apply {
                saveObjects(objects).wait() shouldEqual TaskStatus.Published
                var count = 0

                browseAllObjects(Query(hitsPerPage = 1)) { page ->
                    nbHits shouldEqual 10
                    hits!!.size shouldEqual 1
                    page shouldEqual count
                    count++
                }
                count shouldEqual 10
            }
        }
    }
}