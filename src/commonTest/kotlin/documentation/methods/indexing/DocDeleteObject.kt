package documentation.methods.indexing

import com.algolia.search.model.ObjectID
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocDeleteObject : TestDocumentation() {

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
    fun deletes() {
        runBlocking {
            val objectIDS = listOf(ObjectID("myID1"), ObjectID("myID2"))

            index.deleteObjects(objectIDS)
        }
    }

    @Test
    fun delete() {
        runBlocking {
            val objectID = ObjectID("myID1")

            index.deleteObject(objectID)
        }
    }
}