package documentation.methods.synonym

import com.algolia.search.model.ObjectID
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocGetSynonym {

//    suspend fun Index.getSynonym(
//        #{objectID}: __ObjectID__,
//        requestOptions: __RequestOptions?__ = null
//    ): Synonym

    @Test
    fun getSynonyms() {
        runBlocking {
            index.getSynonym(ObjectID("myID"))
        }
    }
}