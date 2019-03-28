package snippets.methods.indexing

import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import io.ktor.client.features.ResponseException
import kotlinx.serialization.Serializable
import runBlocking
import shouldFailWith
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetGetObjects: TestSnippets() {

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

    @Test
    fun getObject() {
        shouldFailWith<ResponseException> {
            runBlocking {
                index.getObject(ObjectID("myID1"))
            }
        }
    }

    @Test
    fun getObjectSerializer() {
        shouldFailWith<ResponseException> {
            runBlocking {
                @Serializable
                data class Person(
                    val firstname: String,
                    val lastname: String,
                    override val objectID: ObjectID
                ) : Indexable

                val objectID = ObjectID("myID1")

                index.getObject(objectID)
                index.getObject(Person.serializer(), objectID)
            }
        }
    }

    @Test
    fun getObjects() {
        runBlocking {
            index.getObjects(listOf(ObjectID("myID1"), ObjectID("myID2")))
        }
    }

    @Test
    fun getObjectAttribute() {
        runBlocking {
            val objectIDs = listOf(ObjectID("myID1"), ObjectID("myID2"))
            val attributes = listOf(Attribute("firstname"), Attribute("lastname"))

            index.getObjects(objectIDs, attributes)
        }
    }
}