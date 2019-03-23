package snippets.indexing

import clientAdmin1
import com.algolia.search.model.ObjectID
import runBlocking
import suite.cleanIndex
import suite.testSuiteIndexName
import kotlin.test.BeforeTest
import kotlin.test.Test


internal class SnippetDeleteObject {

//    suspend fun deleteObject(
//        objectID: __ObjectID__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): DeletionObject
//
//    suspend fun deleteObjects(
//        objectIDs: __List<ObjectID>__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatch

    private val suffix = "snippet"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @BeforeTest
    fun clean() {
        runBlocking { cleanIndex(clientAdmin1, suffix) }
    }

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