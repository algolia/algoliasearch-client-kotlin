package documentation.methods.synonym

import com.algolia.search.model.ObjectID
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocGetSynonym {

//    suspend fun Index.getSynonym(
//        #{objectID}: __ObjectID__,
//        requestOptions: __RequestOptions?__ = null
//    ): Synonym

    @Test
    fun snippet1() {
        runBlocking {
            index.getSynonym(ObjectID("myID"))
        }
    }
}
