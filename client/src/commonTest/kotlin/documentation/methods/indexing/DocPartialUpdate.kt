package documentation.methods.indexing

import com.algolia.search.dsl.requestOptions
import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Partial
import com.algolia.search.model.multicluster.UserID
import documentation.index
import kotlinx.coroutines.test.runTest
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
//        partials: __List<Pair<ObjectID, Partial>>__,
//        #{createIfNotExists}: __Boolean__ = true,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatch

    @Test
    fun snippet1() {
        runTest {
            val partial = Partial.Update(Attribute("city"), "San Francisco")

            index.partialUpdateObject(ObjectID("myID"), partial)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val partial = Partial.Update(Attribute("state"), "California")

            index.partialUpdateObject(ObjectID("myID"), partial)
        }
    }

    @Test
    fun snippet3() {
        runTest {
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
        runTest {
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

    @Test
    fun snippet6() {
        runTest {
            val partial = Partial.IncrementFrom(Attribute("version"), 2)
            index.partialUpdateObject(ObjectID("myID"), partial)
        }
    }

    @Test
    fun snippet7() {
        runTest {
            val partial = Partial.IncrementSet(Attribute("lastmodified"), 1593431913)
            index.partialUpdateObject(ObjectID("myID"), partial)
        }
    }
}
