package suite

import clientAdmin1
import com.algolia.search.exception.AlgoliaApiException
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymQuery
import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonObject
import shouldBeTrue
import shouldContain
import shouldEqual
import shouldFailWith
import kotlin.test.Test

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
        playstation, Synonym.Placeholder.Token("PLAYSTATIONVERSION"), listOf("1", "One", "2", "3", "4", "4 pro")
    )
    private val synonymAlternative1 =
        Synonym.AlternativeCorrections(ps4, "ps4", listOf("playstation4"), SynonymType.Typo.One)
    private val synonymAlternative2 =
        Synonym.AlternativeCorrections(psone, "psone", listOf("playstationone"), SynonymType.Typo.Two)
    private val synonyms = listOf(synonymOneWay, synonymPlaceholder, synonymAlternative1, synonymAlternative2)
    private val index = clientAdmin1.initIndex(indexName)

    @Test
    fun test() = runTest {
        val objects = load(ListSerializer(JsonObject.serializer()), "console.json")
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
                val synonyms = it.hits.map { hit -> hit.synonym }

                synonyms shouldContain synonymOneWay
                synonyms shouldContain synonymMultiWay
                synonyms shouldContain synonymPlaceholder
                synonyms shouldContain synonymAlternative1
                synonyms shouldContain synonymAlternative2
            }
            deleteSynonym(gba).wait() shouldEqual TaskStatus.Published
            (
                shouldFailWith<AlgoliaApiException> {
                    getSynonym(gba)
                }
                ).httpErrorCode shouldEqual HttpStatusCode.NotFound.value

            clearSynonyms().wait() shouldEqual TaskStatus.Published
            searchSynonyms(SynonymQuery(page = 0, hitsPerPage = 10)).nbHits shouldEqual 0
        }
    }
}
