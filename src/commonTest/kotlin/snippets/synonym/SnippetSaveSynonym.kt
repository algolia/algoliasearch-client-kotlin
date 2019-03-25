package snippets.synonym

import com.algolia.search.model.ObjectID
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetSaveSynonym : TestSnippets() {

//    suspend fun Index.saveSynonym(
//        #{synonym}: __Synonym__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionSynonym

    @Test
    fun saveSynonymMultiWay() {
        runBlocking {
            val synonym = Synonym.MultiWay(
                objectID = ObjectID("myID"),
                synonyms = listOf("car", "vehicle", "auto")
            )

            index.saveSynonym(synonym, forwardToReplicas = true)
        }
    }

    @Test
    fun saveSynonymOneWay() {
        runBlocking {
            val synonym = Synonym.OneWay(
                objectID = ObjectID("myID"),
                input = "car",
                synonyms = listOf("vehicle", "auto")
            )

            index.saveSynonym(synonym, forwardToReplicas = true)
        }
    }

    @Test
    fun saveSynonymAlternative() {
        runBlocking {
            val synonym = Synonym.AlternativeCorrections(
                objectID = ObjectID("myID"),
                corrections = listOf("vehicle", "auto"),
                typo = SynonymType.Typo.Two,
                word = "car"
            )

            index.saveSynonym(synonym, forwardToReplicas = true)
        }
    }

    @Test
    fun saveSynonymPlaceholder() {
        runBlocking {
            val synonym = Synonym.Placeholder(
                objectID = ObjectID("myID"),
                placeholder = Synonym.Placeholder.Token("street"),
                replacements = listOf("street", "st")
            )

            index.saveSynonym(synonym, forwardToReplicas = true)
        }
    }
}