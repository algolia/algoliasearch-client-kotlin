package suite

import com.algolia.search.client.ClientAccount
import com.algolia.search.model.queryrule.QueryRule
import com.algolia.search.model.settings.SearchableAttribute
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.toAttribute
import com.algolia.search.toObjectID
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSuiteAccount {

    private val suffix1 = "copy_index"
    private val suffix2 = "copy_index_2"
    private val indexName1 = testSuiteIndexName(suffix1)
    private val indexName2 = testSuiteIndexName(suffix2)
    private val index1 = clientAdmin1.getIndex(indexName1)
    private val index2 = clientAdmin1.getIndex(indexName2)
    private val index3 = clientAdmin2.getIndex(indexName2)
    private val objectID = "one".toObjectID()
    private val json = Json(indented = true, indent = "  ", encodeDefaults = false)
    private val data = json { KeyObjectID to objectID.raw }
    private val synonym = Synonym.MultiWay(objectID, synonyms = listOf("one", "two"))
    private val settings =
        Settings(searchableAttributes = listOf(SearchableAttribute.Default("objectID".toAttribute())))

    private fun loadQueryRule(): QueryRule {
        val string = loadScratch("query_rule_one.json").readText()
        val queryRule = json.parse(QueryRule.serializer(), string)
        val serialized = json.stringify(QueryRule.serializer(), queryRule)

        serialized shouldEqual string
        return queryRule
    }

    @Before
    fun clean() {
        cleanIndex(clientAdmin1, suffix1)
        cleanIndex(clientAdmin2, suffix2)
    }

    @Test
    fun test() {
        runBlocking {
            val rule = loadQueryRule()
            var hasThrown = false
            try {
                ClientAccount.copyIndex(index1, index2)
            } catch (exception: Exception) {
                hasThrown = true
            }
            hasThrown.shouldBeTrue()
            val tasks = mutableListOf<Task>()

            index1.apply {
                tasks += saveObject(data)
                tasks += saveSynonym(synonym)
                tasks += setSettings(settings)
                tasks += saveRule(rule)
                tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()
            }
            index3.apply {
                ClientAccount.copyIndex(index1, this).wait().all { it is TaskStatus.Published }.shouldBeTrue()

                getObject(objectID) shouldEqual data
                getSynonym(objectID) shouldEqual synonym
                getRule(objectID).queryRule shouldEqual rule
                getSettings().searchableAttributes shouldEqual settings.searchableAttributes
            }
        }
    }
}