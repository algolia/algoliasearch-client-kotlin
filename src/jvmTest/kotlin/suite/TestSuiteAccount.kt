package suite

import com.algolia.search.client.ClientAccount
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.settings.SearchableAttribute
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.toAttribute
import com.algolia.search.toObjectID
import kotlinx.coroutines.runBlocking
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
    private val index1 = clientAdmin1.initIndex(indexName1)
    private val index2 = clientAdmin1.initIndex(indexName2)
    private val index3 = clientAdmin2.initIndex(indexName2)
    private val objectID = "one".toObjectID()
    private val data = json { KeyObjectID to objectID.raw }
    private val synonym = Synonym.MultiWay(objectID, synonyms = listOf("one", "two"))
    private val settings =
        Settings(searchableAttributes = listOf(SearchableAttribute.Default("objectID".toAttribute())))


    @Before
    fun clean() {
        runBlocking {
            cleanIndex(clientAdmin1, suffix1)
            cleanIndex(clientAdmin2, suffix2)
        }
    }

    @Test
    fun test() {
        runBlocking {
            val rule = load(Rule.serializer(), "rule_one.json")
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
                tasks += saveRule(rule)
                tasks += saveSynonym(synonym)
                tasks += setSettings(settings)
                tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()
            }
            index3.apply {
                ClientAccount.copyIndex(index1, this).wait().all { it is TaskStatus.Published }.shouldBeTrue()

                getObject(objectID) shouldEqual data
                getSynonym(objectID) shouldEqual synonym
                getRule(objectID).rule shouldEqual rule
                getSettings().searchableAttributes shouldEqual settings.searchableAttributes
            }
        }
    }
}