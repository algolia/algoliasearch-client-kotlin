package documentation.methods.indexing

import com.algolia.search.dsl.requestOptions
import com.algolia.search.model.ObjectID
import com.algolia.search.model.multicluster.UserID
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocDeleteObject {

//    suspend fun Index.deleteObject(
//        #{objectID}: __ObjectID__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): DeletionObject
//
//    suspend fun Index.deleteObjects(
//        #{objectIDs}: __List<ObjectID>__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatch

    @Test
    fun snippet1() {
        runTest {
            val objectIDS = listOf(ObjectID("myID1"), ObjectID("myID2"))

            index.deleteObjects(objectIDS)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val objectID = ObjectID("myID1")

            index.deleteObject(objectID)
        }
    }

    @Test
    fun snippet3() {
        runTest {
            val objectIDS = listOf(ObjectID("myID1"), ObjectID("myID2"))
            val requestOptions = requestOptions {
                headerAlgoliaUserId(UserID("user123"))
            }

            index.deleteObjects(objectIDS, requestOptions)
        }
    }
}
