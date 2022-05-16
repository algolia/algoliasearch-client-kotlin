package suite

import clientAdmin1
import clientAdmin2
import com.algolia.search.client.ClientAccount
import com.algolia.search.helper.toAttribute
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.settings.SearchableAttribute
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.internal.Key
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import shouldBeTrue
import shouldEqual
import shouldFailWith
import kotlin.test.Test

internal class TestSuiteAccount {

    private val suffix1 = "copy_index"
    private val suffix2 = "copy_index_2"
    private val indexName1 = testSuiteIndexName(suffix1)
    private val indexName2 = testSuiteIndexName(suffix2)
    private val index1 = clientAdmin1.initIndex(indexName1)
    private val index2 = clientAdmin1.initIndex(indexName2)
    private val index3 = clientAdmin2.initIndex(indexName2)
    private val objectID = "one".toObjectID()
    private val data = buildJsonObject { put(Key.ObjectID, objectID.raw) }
    private val synonym = Synonym.MultiWay(objectID, synonyms = listOf("one", "two"))
    private val settings =
        Settings(searchableAttributes = listOf(SearchableAttribute.Default("objectID".toAttribute())))

    @Test
    fun test() {
        runTest {
            val rule = load(Rule.serializer(), "rule_one.json")

            shouldFailWith<IllegalArgumentException> {
                ClientAccount.copyIndex(index1, index2)
            }
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
                getRule(objectID) shouldEqual rule
                getSettings().searchableAttributes shouldEqual settings.searchableAttributes

                shouldFailWith<IllegalStateException> { ClientAccount.copyIndex(index1, this) }
            }
        }
    }
}
