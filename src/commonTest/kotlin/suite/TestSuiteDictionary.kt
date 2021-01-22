package suite

import clientAdmin2
import com.algolia.search.endpoint.extension.deletePluralsEntries
import com.algolia.search.endpoint.extension.deleteStopwordsEntries
import com.algolia.search.endpoint.extension.replaceStopwordsEntries
import com.algolia.search.endpoint.extension.savePluralsEntries
import com.algolia.search.endpoint.extension.saveStopwordsEntries
import com.algolia.search.endpoint.extension.searchPluralsEntries
import com.algolia.search.endpoint.extension.searchStopwordsEntries
import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.model.dictionary.DictionarySettings
import com.algolia.search.model.dictionary.DisableStandardEntries
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.Query
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import runBlocking

internal class TestSuiteDictionary {

    @Test
    fun testStopwordsDictionaries(): Unit = runBlocking {
        val entry = DictionaryEntry.Stopword(
            objectID = ObjectID(UUID.randomUUID().toString()),
            language = Language.English,
            word = "upper"
        )

        // Search for non existent.
        assertEquals(0, clientAdmin2.searchStopwordsEntries(query = Query(entry.objectID.raw)).nbHits)

        // Save entry
        clientAdmin2.run {
            saveStopwordsEntries(listOf(entry)).wait()
            val response = searchStopwordsEntries(query = Query(entry.objectID.raw))
            assertEquals(1, response.nbHits)
            assertEquals(entry, response.hits[0])
        }

        // Delete entry
        clientAdmin2.run {
            deleteStopwordsEntries(listOf(entry.objectID)).wait()
            val response = searchStopwordsEntries(query = Query(entry.objectID.raw))
            assertEquals(0, response.nbHits)
        }

        // Replace
        clientAdmin2.run {
            val oldDictionaryState = searchStopwordsEntries(Query(""))
            val oldDictionaryEntries = oldDictionaryState.hits.filter { it.type == DictionaryEntry.Type.Custom }

            saveStopwordsEntries(listOf(entry)).wait()
            assertEquals(1, searchStopwordsEntries(query = Query(entry.objectID.raw)).nbHits)

            replaceStopwordsEntries(oldDictionaryEntries).wait()
            replaceStopwordsEntries(listOf(entry.copy(word = "uppercase")))
            assertEquals(0, searchStopwordsEntries(query = Query(entry.objectID.raw)).nbHits)
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
        assertEquals(0, clientAdmin2.searchPluralsEntries(query = Query(entry.objectID.raw)).nbHits)

        // Save
        clientAdmin2.run {
            savePluralsEntries(listOf(entry)).wait()
            val response = searchPluralsEntries(query = Query(entry.objectID.raw))
            assertEquals(1, response.nbPages)
            assertEquals(entry, response.hits[0])
        }

        // Delete
        clientAdmin2.run {
            deletePluralsEntries(objectIDs = listOf(entry.objectID)).wait()
            assertEquals(0, searchPluralsEntries(query = Query(entry.objectID.raw)).nbHits)
        }
    }
}
