package documentation.methods.indexing

import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Partial
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocPartialUpdate: TestDocumentation() {

//    suspend fun Index.partialUpdateObject(
//        objectID: __ObjectID__,
//        partial: __Partial__,
//        #{createIfNotExists}: __Boolean?__ = null,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): RevisionObject
//
//    suspend fun Index.partialUpdateObjects(
//        data: __List<Pair<ObjectID, Partial>>__,
//        #{createIfNotExists}: __Boolean__ = true,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatch

    @Test
    fun partialUpdates() {
        runBlocking {
            val firstname = Attribute("firstname")
            val partials = listOf(
                ObjectID("myID1") to Partial.Update(firstname, "Jimmie"),
                ObjectID("myID2") to Partial.Update(firstname, "Warren")
            )

            index.partialUpdateObjects(partials)
        }
    }

    @Test
    fun partialUpdate() {
        runBlocking {
            val partial = Partial.Update(Attribute("city"), "San Francisco")

            index.partialUpdateObject(ObjectID("myID"), partial)
        }
    }

    @Test
    fun partialUpdateAdd() {
        runBlocking {
            val partial = Partial.Update(Attribute("state"), "California")

            index.partialUpdateObject(ObjectID("myID"), partial)
        }
    }
}