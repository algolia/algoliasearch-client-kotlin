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
import runBlocking
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TestSuiteDictionary {

    @Test
    fun testStopwordsDictionaries(): Unit = runBlocking {
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

        // Delete entry
        clientAdmin2.run {
            deleteStopwordEntries(listOf(entry.objectID)).wait()
            val response = searchStopwordEntries(query = Query(entry.objectID.raw))
            assertEquals(0, response.nbHits)
        }

        // Replace
        clientAdmin2.run {
            val oldDictionaryState = searchStopwordEntries(Query(""))
            val oldDictionaryEntries = oldDictionaryState.hits.filter { it.type == DictionaryEntry.Type.Custom }

            saveStopwordEntries(listOf(entry)).wait()
            assertEquals(1, searchStopwordEntries(query = Query(entry.objectID.raw)).nbHits)

            replaceStopwordEntries(oldDictionaryEntries).wait()
            replaceStopwordEntries(listOf(entry.copy(word = "uppercase")))
            assertEquals(0, searchStopwordEntries(query = Query(entry.objectID.raw)).nbHits)
        }

        // Settings
        clientAdmin2.run {
            val stopwordsSettings = DictionarySettings(
                disableStandardEntries = DisableStandardEntries(
                    stopwords = mapOf(Language.English to true)
                )
            )
            setDictionarySettings(stopwordsSettings).wait()
            assertEquals(stopwordsSettings, getDictionarySettings())
        }
    }

    @Test
    fun testPluralsDictionaries(): Unit = runBlocking {
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
    fun testCompoundsDictionaries(): Unit = runBlocking {
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
}
