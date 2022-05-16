package suite

import clientAdmin2
import com.algolia.search.endpoint.extension.deleteCompoundEntries
import com.algolia.search.endpoint.extension.deletePluralEntries
import com.algolia.search.endpoint.extension.deleteStopwordEntries
import com.algolia.search.endpoint.extension.replaceStopwordEntries
import com.algolia.search.endpoint.extension.saveCompoundEntries
import com.algolia.search.endpoint.extension.savePluralEntries
import com.algolia.search.endpoint.extension.saveStopwordEntries
import com.algolia.search.endpoint.extension.searchCompoundEntries
import com.algolia.search.endpoint.extension.searchPluralEntries
import com.algolia.search.endpoint.extension.searchStopwordEntries
import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.model.dictionary.DictionarySettings
import com.algolia.search.model.dictionary.DisableStandardEntries
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.Query
import kotlinx.coroutines.test.runTest
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TestSuiteDictionary {

    @Test
    fun testStopwordsDictionaries(): Unit = runTest {
        val entry = DictionaryEntry.Stopword(
            objectID = ObjectID(UUID.randomUUID().toString()),
            language = Language.English,
            word = "upper"
        )

        // Search for non existent.
        assertEquals(0, clientAdmin2.searchStopwordEntries(query = Query(entry.objectID.raw)).nbHits)

        // Save entry
        clientAdmin2.run {
            saveStopwordEntries(listOf(entry)).wait()
            val response = searchStopwordEntries(query = Query(entry.objectID.raw))
            assertEquals(1, response.nbHits)
            assertEquals(entry, response.hits[0])
        }

        // Replace
        clientAdmin2.run {
            val newWord = "uppercase"
            replaceStopwordEntries(listOf(entry.copy(word = newWord))).wait()
            val responseSearchDictionaries = searchStopwordEntries(query = Query(entry.objectID.raw))
            assertEquals(1, responseSearchDictionaries.nbHits)
            assertEquals(newWord, responseSearchDictionaries.hits[0].word)
        }

        // Delete entry
        clientAdmin2.run {
            deleteStopwordEntries(listOf(entry.objectID)).wait()
            val response = searchStopwordEntries(query = Query(entry.objectID.raw))
            assertEquals(0, response.nbHits)
        }
    }

    @Test
    fun testPluralsDictionaries(): Unit = runTest {
        val entry = DictionaryEntry.Plural(
            objectID = ObjectID(UUID.randomUUID().toString()),
            language = Language.French,
            words = listOf("cheval", "chevaux")
        )

        // Search for non existent.
        assertEquals(0, clientAdmin2.searchPluralEntries(query = Query(entry.objectID.raw)).nbHits)

        // Save
        clientAdmin2.run {
            savePluralEntries(listOf(entry)).wait()
            val response = searchPluralEntries(query = Query(entry.objectID.raw))
            assertEquals(1, response.nbPages)
            assertEquals(entry, response.hits[0])
        }

        // Delete
        clientAdmin2.run {
            deletePluralEntries(objectIDs = listOf(entry.objectID)).wait()
            assertEquals(0, searchPluralEntries(query = Query(entry.objectID.raw)).nbHits)
        }
    }

    @Test
    fun testCompoundsDictionaries(): Unit = runTest {
        val entry = DictionaryEntry.Compound(
            objectID = ObjectID(UUID.randomUUID().toString()),
            language = Language.Dutch,
            word = "kopfschmerztablette",
            decomposition = listOf("kopf", "schmerz", "tablette")
        )

        // Save
        clientAdmin2.run {
            saveCompoundEntries(listOf(entry)).wait()
            val response = searchCompoundEntries(query = Query(entry.objectID.raw))
            assertEquals(1, response.nbHits)
            assertEquals(entry, response.hits[0])
        }

        // Delete
        clientAdmin2.run {
            deleteCompoundEntries(listOf(entry.objectID)).wait()
            assertEquals(0, searchCompoundEntries(query = Query(entry.objectID.raw)).nbHits)
        }
    }

    @Test
    fun testSettings(): Unit = runTest {
        clientAdmin2.run {
            val stopwords: Map<Language, Boolean> = mapOf(Language.English to true)
            val stopwordsSettings = DictionarySettings(
                disableStandardEntries = DisableStandardEntries(
                    stopwords = stopwords
                )
            )
            setDictionarySettings(stopwordsSettings).wait()
            val response = getDictionarySettings()
            assert(response.disableStandardEntries?.stopwords?.entries?.containsAll(stopwords.entries) == true)
        }
    }
}
