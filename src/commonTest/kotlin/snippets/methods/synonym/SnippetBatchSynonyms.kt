package snippets.methods.synonym

import com.algolia.search.model.ObjectID
import com.algolia.search.model.synonym.Synonym
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetBatchSynonyms : TestSnippets() {

//    suspend fun Index.saveSynonyms(
//        #{synonyms}: __List<Synonym>__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        #{replaceExistingSynonyms}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun saveSynonyms() {
        runBlocking {
            val synonyms = listOf(
                Synonym.MultiWay(
                    objectID = ObjectID("myID1"),
                    synonyms = listOf("car", "vehicle", "auto")
                ),
                Synonym.MultiWay(
                    objectID = ObjectID("myID2"),
                    synonyms = listOf("street", "st")
                )
            )
            index.saveSynonyms(synonyms, forwardToReplicas = true, replaceExistingSynonyms = true)
        }
    }
}