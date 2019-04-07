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
internal class DocSaveObjects {

//    suspend fun <T : Indexable> Index.replaceObject(
//        serializer: __KSerializer<T>__,
//        [data](#method-param-objects): __T__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): RevisionObject
//
//    suspend fun <T : Indexable> Index.replaceObjects(
//        serializer: __KSerializer<T>__,
//        [data](#method-param-objects): __List<T>__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatch
//
//    suspend fun Index.replaceObject(
//        [objectID](#method-param-objectidkey): __ObjectID__,
//        [data](#method-param-objects): __JsonObject__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): RevisionObject
//
//    suspend fun Index.replaceObjects(
//        [data](#method-param-objects): __List<Pair<ObjectID, JsonObject>>__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatch

    @Test
    fun replaceObjects() {
        runBlocking {
            // With JsonObject
            val json = listOf(
                ObjectID("myID1") to json {
                    "firstname" to "Jimmie"
                    "lastname" to "Barninger"
                },
                ObjectID("myID1") to json {
                    "firstname" to "Warren"
                    "lastname" to "Speach"
                }
            )

            index.replaceObjects(json)

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

            index.replaceObjects(Contact.serializer(), contacts)
        }
    }

    @Test
    fun replaceObject() {
        runBlocking {
            // With JsonObject
            val json = json {
                "firstname" to "Jimmie"
                "lastname" to "Barninger"
                "city" to "New York"
            }

            index.replaceObject(ObjectID("myID"), json)

            // With serializable class
            @Serializable
            data class Contact(
                val firstname: String,
                val lastname: String,
                val city: String,
                override val objectID: ObjectID
            ) : Indexable

            val contact = Contact("Jimmie", "Barninger", "New York", ObjectID("myID"))

            index.replaceObject(Contact.serializer(), contact)
        }
    }

    @Test
    fun replaceExtraHeader() {
        runBlocking {
            val json = listOf(
                ObjectID("myID1") to json {
                    "firstname" to "Jimmie"
                    "lastname" to "Barninger"
                },
                ObjectID("myID1") to json {
                    "firstname" to "Warren"
                    "lastname" to "Speach"
                }
            )
            val requestOptions = requestOptions {
                headerAlgoliaUserId(UserID("user123"))
            }

            index.replaceObjects(json, requestOptions)
        }
    }
}