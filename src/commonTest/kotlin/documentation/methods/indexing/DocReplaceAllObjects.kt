package documentation.methods.indexing

import documentation.client
import documentation.index
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.json
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocReplaceAllObjects {

//    suspend fun Index.replaceAllObjects(
//        [records](#method-param-objects): __List<JsonObject>__
//    ): List<Task>
//
//    suspend fun <T : Indexable> Index.replaceAllObjects(
//        serializer: KSerializer<T>,
//        [records](#method-param-objects): List<T>
//    ): List<Task>

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

            index.replaceAllObjects(json)

            // With serializable class
            @Serializable
            data class Contact(val firstname: String, val lastname: String)

            val contacts = listOf(
                Contact("Jimmie", "Barninger"),
                Contact("Warren", "Speach")
            )

            index.replaceAllObjects(Contact.serializer(), contacts)
        }
    }

    @Test
    fun snippet2() {
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