package snippets.indexing

import clientAdmin1
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.json
import runBlocking
import suite.cleanIndex
import suite.testSuiteIndexName
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test


internal class SnippetReplaceAllObjects {

//    suspend fun replaceAllObjects(
//        [data](#method-param-objects): __List<JsonObject>__
//    ): List<Task>
//
//    suspend fun <T : Indexable> replaceAllObjects(
//        serializer: KSerializer<T>,
//        [data](#method-param-objects): List<T>
//    ): List<Task>

    private val suffix = "snippet"
    private val indexName = testSuiteIndexName(suffix)
    private val client = clientAdmin1
    private val index = client.initIndex(indexName)

    @BeforeTest
    fun clean() {
        runBlocking { cleanIndex(client, suffix) }
    }

    @Test
    fun replaceAllObjects() {
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

            index.replaceAllObjects(json)

            // With serializable class
            @Serializable
            data class Person(val firstname: String, val lastname: String)

            val persons = listOf(
                Person("Jimmie", "Barninger"),
                Person("Warren", "Speach")
            )

            index.replaceAllObjects(Person.serializer(), persons)
        }
    }

    @Test
    fun replaceAllObjectsWait() {
        runBlocking {
            client.apply {
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

                index.replaceAllObjects(json).waitAll()
            }
        }
    }
}