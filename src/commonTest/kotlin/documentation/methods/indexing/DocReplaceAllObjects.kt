package documentation.methods.indexing

import documentation.TestDocumentation
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.json
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocReplaceAllObjects : TestDocumentation() {

//    suspend fun Index.replaceAllObjects(
//        [data](#method-param-objects): __List<JsonObject>__
//    ): List<Task>
//
//    suspend fun <T : Indexable> Index.replaceAllObjects(
//        serializer: KSerializer<T>,
//        [data](#method-param-objects): List<T>
//    ): List<Task>

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