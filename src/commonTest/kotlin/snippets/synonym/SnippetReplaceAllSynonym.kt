package snippets.synonym

import com.algolia.search.model.synonym.Synonym
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetReplaceAllSynonym : TestSnippets() {

//    suspend fun Index.replaceAllSynonyms(
//        #{synonyms}: __List<Synonym>__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun synonyms() {
        runBlocking {
            // Fetch your synonyms
            val synonyms = listOf<Synonym>()

            index.replaceAllSynonyms(synonyms, forwardToReplicas = true)
        }
    }
}