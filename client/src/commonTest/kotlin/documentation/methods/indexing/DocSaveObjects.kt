package documentation.methods.indexing

import com.algolia.search.dsl.requestOptions
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.multicluster.UserID
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocSaveObjects {

//    suspend fun <T : Indexable> Index.replaceObject(
//        serializer: __KSerializer<T>__,
//        [record](#method-param-objects): __T__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): RevisionObject
//
//    suspend fun <T : Indexable> Index.replaceObjects(
//        serializer: __KSerializer<T>__,
//        [records](#method-param-objects): __List<T>__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatch
//
//    suspend fun Index.replaceObject(
//        [objectID](#method-param-objectidkey): __ObjectID__,
//        [record](#method-param-objects): __JsonObject__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): RevisionObject
//
//    suspend fun Index.replaceObjects(
//        [records](#method-param-objects): __List<Pair<ObjectID, JsonObject>>__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatch

    @Serializable
    data class Contact(
        val firstname: String,
        val lastname: String,
        val city: String? = null,
        override val objectID: ObjectID
    ) : Indexable

    @Test
    fun snippet1() {
        runTest {
            // With JsonObject
            val json = listOf(
                ObjectID("myID1") to buildJsonObject {
                    put("firstname", "Jimmie")
                    put("lastname", "Barninger")
                },
                ObjectID("myID1") to buildJsonObject {
                    put("firstname", "Warren")
                    put("lastname", "Speach")
                }
            )

            index.replaceObjects(json)

            // With serializable class
            val contacts = listOf(
                Contact(firstname = "Jimmie", lastname = "Barninger", objectID = ObjectID("myID")),
                Contact(firstname = "Jimmie", lastname = "Barninger", objectID = ObjectID("myID"))
            )

            index.replaceObjects(Contact.serializer(), contacts)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            // With JsonObject
            val json = buildJsonObject {
                put("firstname", "Jimmie")
                put("lastname", "Barninger")
                put("city", "New York")
            }

            index.replaceObject(ObjectID("myID"), json)

            // With serializable class
            val contact = Contact("Jimmie", "Barninger", "New York", ObjectID("myID"))

            index.replaceObject(Contact.serializer(), contact)
        }
    }

    @Test
    fun snippet3() {
        runTest {
            val json = listOf(
                ObjectID("myID1") to buildJsonObject {
                    put("firstname", "Jimmie")
                    put("lastname", "Barninger")
                },
                ObjectID("myID1") to buildJsonObject {
                    put("firstname", "Warren")
                    put("lastname", "Speach")
                }
            )
            val requestOptions = requestOptions {
                headerAlgoliaUserId(UserID("user123"))
            }

            index.replaceObjects(json, requestOptions)
        }
    }
}
