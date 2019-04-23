package documentation.methods.indexing

import com.algolia.search.dsl.requestOptions
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.multicluster.UserID
import documentation.index
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.json
import runBlocking
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

    @Test
    fun snippet1() {
        runBlocking {
            // With JsonObject
            val json = listOf(
                json {
                    "firstname" to "Jimmie"
                    "lastname" to "Barninger"
                },
                json {
                    "firstname" to "Warren"
                    "lastname" to "Speach"
                }
            )

            index.saveObjects(json)

            // With serializable class
            @Serializable
            data class Contact(val firstname: String, val lastname: String)

            val contacts = listOf(
                Contact("Jimmie", "Barninger"),
                Contact("Warren", "Speach")
            )

            index.saveObjects(Contact.serializer(), contacts)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            // With JsonObject
            val json = listOf(
                json {
                    "objectID" to ObjectID("myID1")
                    "firstname" to "Jimmie"
                    "lastname" to "Barninger"
                },
                json {
                    "objectID" to ObjectID("myID2")
                    "firstname" to "Warren"
                    "lastname" to "Speach"
                }
            )

            index.saveObjects(json)

            // With serializable class
            @Serializable
            data class Contact(
                val firstname: String,
                val lastname: String,
                override val objectID: ObjectID
            ) : Indexable

            val contacts = listOf(
                Contact("Jimmie", "Barninger", ObjectID("myID")),
                Contact("Jimmie", "Barninger", ObjectID("myID"))
            )

            index.saveObjects(Contact.serializer(), contacts)
        }
    }

    @Test
    fun snippet3() {
        runBlocking {
            // With JsonObject
            val json = json {
                "firstname" to "Jimmie"
                "lastname" to "Barninger"
                "objectID" to "myID"
            }

            index.saveObject(json)

            /// With serializable class
            @Serializable
            data class Contact(
                val firstname: String,
                val lastname: String,
                override val objectID: ObjectID
            ) : Indexable

            val contact = Contact("Jimmie", "Barninger", ObjectID("myID"))

            index.saveObject(Contact.serializer(), contact)
        }
    }

    @Test
    fun snippet4() {
        runBlocking {
            val json = listOf(
                json {
                    "objectID" to ObjectID("myID1")
                    "firstname" to "Jimmie"
                    "lastname" to "Barninger"
                },
                json {
                    "objectID" to ObjectID("myID2")
                    "firstname" to "Warren"
                    "lastname" to "Speach"
                }
            )
            val requestOptions = requestOptions {
                headerAlgoliaUserId(UserID("user123"))
            }

            index.saveObjects(json, requestOptions)
        }
    }
}