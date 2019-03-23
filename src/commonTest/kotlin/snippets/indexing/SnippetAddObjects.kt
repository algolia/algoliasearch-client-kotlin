package snippets.indexing

import clientAdmin1
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.json
import runBlocking
import suite.cleanIndex
import suite.testSuiteIndexName
import kotlin.test.AfterTest
import kotlin.test.Test


internal class SnippetAddObjects {

//    suspend fun <T> saveObject(
//        serializer: __KSerializer<T>__,
//        data: __T__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): CreationObject
//
//    suspend fun <T> saveObjects(
//        serializer: __KSerializer<T>__,
//        data: __List<T>__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatch
//
//    suspend fun saveObject(
//        data: __JsonObject__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): CreationObject
//
//    suspend fun saveObjects(
//        data: __List<JsonObject>__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseBatch

    private val suffix = "snippet"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @AfterTest
    fun clean() {
        runBlocking { cleanIndex(clientAdmin1, suffix) }
    }

    @Test
    fun saveObjects() {
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
            data class Person(val firstname: String, val lastname: String)

            val persons = listOf(
                Person("Jimmie", "Barninger"),
                Person("Warren", "Speach")
            )

            index.saveObjects(Person.serializer(), persons)
        }
    }

    @Test
    fun saveObjectsWithID() {
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
            data class Person(
                val firstname: String,
                val lastname: String,
                override val objectID: ObjectID
            ) : Indexable

            val persons = listOf(
                Person("Jimmie", "Barninger", ObjectID("myID")),
                Person("Jimmie", "Barninger", ObjectID("myID"))
            )

            index.saveObjects(Person.serializer(), persons)
        }
    }

    @Test
    fun saveObject() {
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
            data class Person(
                val firstname: String,
                val lastname: String,
                override val objectID: ObjectID
            ) : Indexable

            val person = Person("Jimmie", "Barninger", ObjectID("myID"))

            index.saveObject(Person.serializer(), person)
        }
    }
}