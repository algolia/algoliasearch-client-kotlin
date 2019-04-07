package documentation.methods.indexing

import com.algolia.search.dsl.requestOptions
import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Partial
import com.algolia.search.model.multicluster.UserID
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocPartialUpdate {

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
    fun snippet1() {
        runBlocking {
            val partial = Partial.Update(Attribute("city"), "San Francisco")

            index.partialUpdateObject(ObjectID("myID"), partial)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val partial = Partial.Update(Attribute("state"), "California")

            index.partialUpdateObject(ObjectID("myID"), partial)
        }
    }

    @Test
    fun snippet3() {
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
    fun snippet4() {
        runBlocking {
            val firstname = Attribute("firstname")
            val partials = listOf(
                ObjectID("myID1") to Partial.Update(firstname, "Jimmie"),
                ObjectID("myID2") to Partial.Update(firstname, "Warren")
            )
            val requestOptions = requestOptions {
                headerAlgoliaUserId(UserID("user123"))
            }

            index.partialUpdateObjects(partials, requestOptions = requestOptions)
        }
    }
}