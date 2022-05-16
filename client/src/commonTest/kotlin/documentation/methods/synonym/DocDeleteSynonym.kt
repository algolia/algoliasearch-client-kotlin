package documentation.methods.synonym

import com.algolia.search.model.ObjectID
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocDeleteSynonym {

//    suspend fun Index.deleteSynonym(
//        #{objectID}: __ObjectID__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: RequestOptions? = null
//    ): DeletionIndex

    @Test
    fun snippet1() {
        runTest {
            index.deleteSynonym(ObjectID("myID"), forwardToReplicas = true)
        }
    }
}
