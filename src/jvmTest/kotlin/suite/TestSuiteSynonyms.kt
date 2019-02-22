package suite

import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.toObjectID
import io.ktor.client.features.BadResponseStatusException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.list
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldContain
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSuiteSynonyms {

    private val suffix = "synonyms"
    private val indexName = testSuiteIndexName(suffix)
    private val gba = "gba".toObjectID()
    private val wiiToWiiU = "wii_to_wii_u".toObjectID()
    private val playstation = "playstation_version_placeholder".toObjectID()
    private val ps4 = "ps4".toObjectID()
    private val psone = "psone".toObjectID()
    private val synonymMultiWay = Synonym.MultiWay(gba, listOf("gba", "gameboy advance", "game boy advance"))
    private val synonymOneWay = Synonym.OneWay(wiiToWiiU, "wii", listOf("wii U"))
    private val synonymPlaceholder = Synonym.Placeholder(
        playstation,
        Synonym.Placeholder.Token("PLAYSTATIONVERSION"),
        listOf("1", "One", "2", "3", "4", "4 pro")
    )
    private val synonymAlternative1 =
        Synonym.AlternativeCorrections(ps4, "ps4", listOf("playstation4"), SynonymType.Typo.One)
    private val synonymAlternative2 =
        Synonym.AlternativeCorrections(psone, "psone", listOf("playstationone"), SynonymType.Typo.Two)
    private val synonyms =
        listOf(synonymOneWay, synonymPlaceholder, synonymAlternative1, synonymAlternative2)
    private val index = clientAdmin1.initIndex(indexName)


    @Before
    fun clean() {
        runBlocking {
            cleanIndex(clientAdmin1, suffix)
        }
    }

    @Test
    fun test() {
        runBlocking {
            val objects = load(JsonObjectSerializer.list, "console.json")
            val tasks = mutableListOf<Task>()

            index.apply {
                tasks += saveObjects(objects)
                tasks += saveSynonym(synonymMultiWay)
                tasks += saveSynonyms(synonyms)
                tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()
                getSynonym(synonymMultiWay.objectID) shouldEqual synonymMultiWay
                synonyms.forEach { getSynonym(it.objectID) shouldEqual it }
                searchSynonyms().let {
                    it.nbHits shouldEqual 5
                    it.hits shouldContain synonymOneWay
                    it.hits shouldContain synonymMultiWay
                    it.hits shouldContain synonymPlaceholder
                    it.hits shouldContain synonymAlternative1
                    it.hits shouldContain synonymAlternative2
                }
                deleteSynonym(gba).wait() shouldEqual TaskStatus.Published
                var isNotFound = false
                try {
                    getSynonym(gba)
                } catch (exception: BadResponseStatusException) {
                    isNotFound = exception.statusCode == HttpStatusCode.NotFound
                }
                isNotFound shouldEqual true
                clearSynonyms().wait() shouldEqual TaskStatus.Published
                searchSynonyms(page = 0, hitsPerPage = 10).nbHits shouldEqual 0
            }
        }
    }
}