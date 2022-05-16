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
internal class DocAddObjects {

//    suspend fun <T> Index.saveObject(
//        serializer: __KSerializer<T>__,
//        [record](#method-param-objects): __T__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): CreationObject
//
//    suspend fun <T> Index.saveObjects(
//        serializer: __KSerializer<T>__,
//        [records](#method-param-objects): __List<T>__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatch
//
//    suspend fun Index.saveObject(
//        [record](#method-param-objects): __JsonObject__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): CreationObject
//
//    suspend fun Index.saveObjects(
//        [records](#method-param-objects): __List<JsonObject>__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatch

    @Serializable
    data class Contact(
        val firstname: String,
        val lastname: String,
        override val objectID: ObjectID
    ) : Indexable

    @Serializable
    data class MyContact(
        val firstname: String,
        val lastname: String
    )

    @Test
    fun snippet1() {
        runTest {
            // With JsonObject
            val json = listOf(
                buildJsonObject {
                    put("firstname", "Jimmie")
                    put("lastname", "Barninger")
                },
                buildJsonObject {
                    put("firstname", "Warren")
                    put("lastname", "Speach")
                }
            )

            index.saveObjects(json)

            // With serializable class
            val contacts = listOf(
                MyContact("Jimmie", "Barninger"),
                MyContact("Warren", "Speach")
            )

            index.saveObjects(MyContact.serializer(), contacts)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            // With JsonObject
            val json = listOf(
                buildJsonObject {
                    put("objectID", "myID1")
                    put("firstname", "Jimmie")
                    put("lastname", "Barninger")
                },
                buildJsonObject {
                    put("objectID", "myID2")
                    put("firstname", "Warren")
                    put("lastname", "Speach")
                }
            )

            index.saveObjects(json)

            // With serializable class
            val contacts = listOf(
                Contact("Jimmie", "Barninger", ObjectID("myID")),
                Contact("Jimmie", "Barninger", ObjectID("myID"))
            )

            index.saveObjects(Contact.serializer(), contacts)
        }
    }

    @Test
    fun snippet3() {
        runTest {
            // With JsonObject
            val json = buildJsonObject {
                put("firstname", "Jimmie")
                put("lastname", "Barninger")
                put("objectID", "myID")
            }

            index.saveObject(json)

            // / With serializable class
            val contact = Contact("Jimmie", "Barninger", ObjectID("myID"))

            index.saveObject(Contact.serializer(), contact)
        }
    }

    @Test
    fun snippet4() {
        runTest {
            val json = listOf(
                buildJsonObject {
                    put("objectID", "myID1")
                    put("firstname", "Jimmie")
                    put("lastname", "Barninger")
                },
                buildJsonObject {
                    put("objectID", "myID2")
                    put("firstname", "Warren")
                    put("lastname", "Speach")
                }
            )
            val requestOptions = requestOptions {
                headerAlgoliaUserId(UserID("user123"))
            }

            index.saveObjects(json, requestOptions)
        }
    }
}
