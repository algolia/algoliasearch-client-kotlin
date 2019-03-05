package suite

import com.algolia.search.model.rule.Rule
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.helper.toObjectID
import io.ktor.client.features.BadResponseStatusException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual
import shouldFailWith


@RunWith(JUnit4::class)
class TestSuiteReplaceAll {

    private val suffix = "replacing"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)
    private val objectIDOne = "one".toObjectID()
    private val objectIDTwo = "two".toObjectID()

    @Before
    fun clean() {
        runBlocking {
            cleanIndex(clientAdmin1, suffix)
        }
    }

    @Test
    fun test() {
        runBlocking {
            val rule = load(Rule.serializer(), "rule_one.json")
            val synonym = load(Synonym, "synonym_one.json") as Synonym.MultiWay
            val data = json { KeyObjectID to objectIDOne }

            index.apply {
                val tasks = mutableListOf<Task>()

                tasks += saveObject(data)
                tasks += saveSynonym(synonym)
                tasks += saveRule(rule)

                tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()
                tasks.clear()

                tasks += replaceAllObjects(listOf(json { KeyObjectID to objectIDTwo.raw }))
                tasks += replaceAllSynonyms(listOf(synonym.copy(objectID = objectIDTwo)))
                tasks += replaceAllRules(listOf(rule.copy(objectID = objectIDTwo)))

                tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()

                getObject(objectIDTwo).getPrimitive(KeyObjectID).content shouldEqual objectIDTwo.raw
                getRule(objectIDTwo).rule.objectID shouldEqual objectIDTwo
                getSynonym(objectIDTwo).objectID shouldEqual objectIDTwo

                (BadResponseStatusException::class shouldFailWith {
                    getObject(objectIDOne)
                }).statusCode.value shouldEqual HttpStatusCode.NotFound.value

                (BadResponseStatusException::class shouldFailWith {
                    getSynonym(objectIDOne)
                }).statusCode.value shouldEqual HttpStatusCode.NotFound.value

                (BadResponseStatusException::class shouldFailWith {
                    getRule(objectIDOne)
                }).statusCode.value shouldEqual HttpStatusCode.NotFound.value
            }
        }
    }
}