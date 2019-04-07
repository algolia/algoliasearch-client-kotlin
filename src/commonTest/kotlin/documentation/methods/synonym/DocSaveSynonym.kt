package documentation.methods.synonym

import com.algolia.search.model.ObjectID
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocSaveSynonym {

//    suspend fun Index.saveSynonym(
//        #{synonym}: __Synonym__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionSynonym

    @Test
    fun snippet1() {
        runBlocking {
            val synonym = Synonym.MultiWay(
                objectID = ObjectID("myID"),
                synonyms = listOf("car", "vehicle", "auto")
            )

            index.saveSynonym(synonym, forwardToReplicas = true)
        }
    }

    @Test
    fun snippet2() {
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
    fun snippet3() {
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
    fun snippet4() {
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