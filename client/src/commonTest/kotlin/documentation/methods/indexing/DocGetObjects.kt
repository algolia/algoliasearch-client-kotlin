package documentation.methods.indexing

import com.algolia.search.dsl.requestOptions
import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.multicluster.UserID
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocGetObjects {

//    suspend fun <T : Indexable> Index.getObject(
//        serializer: __KSerializer<T>__,
//        #{objectID}: __ObjectID__,
//        #{attributesToRetrieve}: __List<Attribute>?__ = null,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): T
//
//    suspend fun Index.getObject(
//        #{objectID}: __ObjectID__,
//        #{attributesToRetrieve}: __List<Attribute>?__ = null,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): JsonObject
//
//    suspend fun Index.getObjects(
//        #{objectIDs}: __List<ObjectID>__,
//        #{attributesToRetrieve}: __List<Attribute>?__ = null,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseObjects

    @Serializable
    data class Contact(
        val firstname: String,
        val lastname: String,
        override val objectID: ObjectID
    ) : Indexable

    @Test
    fun snippet1() {
        runTest {
            index.getObject(ObjectID("myID1"))
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val objectID = ObjectID("myID1")

            index.getObject(objectID)
            index.getObject(Contact.serializer(), objectID)
        }
    }

    @Test
    fun snippet3() {
        runTest {
            index.getObjects(listOf(ObjectID("myID1"), ObjectID("myID2")))
        }
    }

    @Test
    fun snippet4() {
        runTest {
            val objectIDs = listOf(ObjectID("myID1"), ObjectID("myID2"))
            val attributes = listOf(Attribute("firstname"), Attribute("lastname"))

            index.getObjects(objectIDs, attributes)
        }
    }

    @Test
    fun snippet5() {
        runTest {
            val requestOptions = requestOptions {
                headerAlgoliaUserId(UserID("user123"))
            }

            index.getObjects(listOf(ObjectID("myID1"), ObjectID("myID2")), requestOptions = requestOptions)
        }
    }
}
