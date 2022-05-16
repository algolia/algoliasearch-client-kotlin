package suite

import clientAdmin1
import com.algolia.search.helper.toAttribute
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.settings.AttributeForFaceting
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
import kotlin.test.Test

internal class TestSuiteCopyIndex {

    private val suffix = "copy_index"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)
    private val company = "company".toAttribute()
    private val indexNameSettings = indexName.copy(indexName.raw + "_settings")
    private val indexNameRules = indexName.copy(indexName.raw + "_rules")
    private val indexNameSynonyms = indexName.copy(indexName.raw + "_synonyms")
    private val indexNameFullCopy = indexName.copy(indexName.raw + "_full_copy")
    private val ruleID = "company_auto_faceting".toObjectID()

    private val objects = listOf(
        buildJsonObject {
            put(Key.ObjectID, "one")
            put(company.raw, "apple")
        },
        buildJsonObject {
            put(Key.ObjectID, "two")
            put(company.raw, "algolia")
        }
    )
    private val settings = Settings(attributesForFaceting = listOf(AttributeForFaceting.Default(company)))

    @Test
    fun test() = runTest {
        index.apply {
            val tasks = mutableListOf<Task>()
            val synonym = load(Synonym, "synonym_placeholder.json")
            val rule = load(Rule.serializer(), "rule_company.json")

            tasks += saveObjects(objects)
            tasks += setSettings(settings)
            tasks += saveSynonym(synonym)
            tasks += saveRule(rule)

            tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()
            tasks.clear()

            tasks += copySettings(indexNameSettings)
            tasks += copyRules(indexNameRules)
            tasks += copySynonyms(indexNameSynonyms)
            tasks += copyIndex(indexNameFullCopy)

            tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()

            clientAdmin1.initIndex(indexNameSettings).getSettings() shouldEqual getSettings()
            clientAdmin1.initIndex(indexNameRules).getRule(ruleID) shouldEqual getRule(ruleID)
            clientAdmin1.initIndex(indexNameSynonyms)
                .getSynonym(synonym.objectID) shouldEqual getSynonym(synonym.objectID)
            clientAdmin1.initIndex(indexNameFullCopy).also {
                it.getSettings() shouldEqual getSettings()
                it.getRule(ruleID) shouldEqual getRule(ruleID)
                it.getSynonym(synonym.objectID) shouldEqual getSynonym(synonym.objectID)
            }
        }
    }
}
