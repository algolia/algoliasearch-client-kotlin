package suite

import clientAdmin1
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.rule.RuleQuery
import com.algolia.search.model.search.Query
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymQuery
import com.algolia.search.model.task.TaskStatus
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import shouldEqual
import kotlin.test.Test

internal class TestSuiteBrowse {

    private val suffix = "helper"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @Test
    fun rules() {
        runTest {
            val ruleA = load(Rule.serializer(), "rule_brand.json")
            val ruleB = load(Rule.serializer(), "rule_company.json")
            var count = 0

            index.apply {
                saveRules(listOf(ruleA, ruleB)).wait() shouldEqual TaskStatus.Published

                browseRules(RuleQuery(hitsPerPage = 1)).forEach {
                    it.nbHits shouldEqual 2
                    it.hits.size shouldEqual 1
                    count++
                }
                count shouldEqual 2
            }
        }
    }

    @Test
    fun synonyms() {
        runTest {
            val synonymA = Synonym.OneWay("a".toObjectID(), "a", listOf("b"))
            val synonymB = Synonym.Placeholder("b".toObjectID(), Synonym.Placeholder.Token("as"), listOf("sad"))
            var count = 0

            index.apply {
                saveSynonyms(listOf(synonymA, synonymB)).wait() shouldEqual TaskStatus.Published

                browseSynonyms(SynonymQuery(hitsPerPage = 1)).forEach {
                    it.nbHits shouldEqual 2
                    it.hits.size shouldEqual 1
                    count++
                }
                count shouldEqual 2
            }
        }
    }

    @Test
    fun objects() {
        runTest {
            val objects = (0 until 10).map { buildJsonObject { } }

            index.apply {
                saveObjects(objects).wait() shouldEqual TaskStatus.Published
                var count = 0

                browseObjects(Query(hitsPerPage = 1)).forEach {
                    it.nbHits shouldEqual 10
                    it.hits.size shouldEqual 1
                    it.page shouldEqual count
                    count++
                }
                count shouldEqual 10
            }
        }
    }
}
