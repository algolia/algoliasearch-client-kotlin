package documentation.methods.synonym

import com.algolia.search.model.ObjectID
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocGetSynonym {

//    suspend fun Index.getSynonym(
//        #{objectID}: __ObjectID__,
//        requestOptions: __RequestOptions?__ = null
//    ): Synonym

    @Test
    fun snippet1() {
        runTest {
            index.getSynonym(ObjectID("myID"))
        }
    }
}
