package documentation.methods.synonym

import com.algolia.search.model.ObjectID
import com.algolia.search.model.synonym.Synonym
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocBatchSynonyms {

//    suspend fun Index.saveSynonyms(
//        #{synonyms}: __List<Synonym>__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        #{replaceExistingSynonyms}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runTest {
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
            index.saveSynonyms(synonyms, forwardToReplicas = true, clearExistingSynonyms = true)
        }
    }
}
