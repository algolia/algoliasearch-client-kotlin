package suite

import clientAdmin2
import com.algolia.search.endpoint.extension.deleteStopwordsEntries
import com.algolia.search.endpoint.extension.replaceStopwordsEntries
import com.algolia.search.endpoint.extension.saveStopwordsEntries
import com.algolia.search.endpoint.extension.searchStopwordsEntries
import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.Query
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import runBlocking

internal class TestSuiteDictionary {

    @Test
    fun testStopwordsDictionaries() {
        runBlocking {
            val randomID = UUID.randomUUID().toString()
            val entryID = ObjectID(randomID)
            val word = "upper"
            val entry = DictionaryEntry.Stopword(
                objectID = entryID,
                language = Language.English,
                word = word
            )

            // Search non existent.
            assertEquals(0, clientAdmin2.searchStopwordsEntries(query = Query(randomID)).nbHits)

            // Save entry
            clientAdmin2.run {
                saveStopwordsEntries(listOf(entry)).wait()
                val response = searchStopwordsEntries(query = Query(entryID.raw))
                assertEquals(1, response.nbHits)
                assertEquals(entry, response.hits[0])
            }

            // Delete entry
            clientAdmin2.run {
                deleteStopwordsEntries(listOf(entryID)).wait()
                val response = searchStopwordsEntries(query = Query(entryID.raw))
                assertEquals(0, response.nbHits)
            }

            // Replace
            clientAdmin2.run {
                val oldDictionaryState = searchStopwordsEntries(Query(""))
                val oldDictionaryEntries = oldDictionaryState.hits.filter { it.type == DictionaryEntry.Type.Custom }

                saveStopwordsEntries(listOf(entry)).wait()
                assertEquals(1, searchStopwordsEntries(query = Query(entryID.raw)).nbHits)

                replaceStopwordsEntries(oldDictionaryEntries).wait()
                replaceStopwordsEntries(listOf(entry.copy(word = "uppercase")))
                assertEquals(0, searchStopwordsEntries(query = Query(entryID.raw)).nbHits)
            }

            //
            //         old_dictionary_state   = @client.search_dictionary_entries('stopwords', '')
            //         old_dictionary_entries = old_dictionary_state[:hits].map do |hit|
            //           hit.reject { |key| key == :type }
            //         end
            //
            //         @client.save_dictionary_entries!('stopwords', [entry])
            //         assert_equal 1, @client.search_dictionary_entries('stopwords', entry_id)[:nbHits]
            //
            //         @client.replace_dictionary_entries!('stopwords', old_dictionary_entries)
            //         assert_equal 0, @client.search_dictionary_entries('stopwords', entry_id)[:nbHits]
            //
            //         stopwords_settings = {
            //           disableStandardEntries: {
            //             stopwords: {
            //               en: true
            //             }
            //           }
            //         }
            //
            //         @client.set_dictionary_settings!(stopwords_settings)
            //
            //         assert_equal @client.get_dictionary_settings, stopwords_settings
        }
    }
}
