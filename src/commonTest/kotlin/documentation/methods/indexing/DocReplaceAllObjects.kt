package documentation.methods.indexing

import documentation.client
import documentation.index
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
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
                buildJsonObject {
                    put("firstname", "Jimmie")
                    put("lastname", "Barninger")
                },
                buildJsonObject {
                    put("firstname", "Warren")
                    put("lastname", "Speach")
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
                    buildJsonObject {
                        put("firstname", "Jimmie")
                        put("lastname", "Barninger")
                    },
                    buildJsonObject {
                        put("firstname", "Warren")
                        put("lastname", "Speach")
                    }
                )

                index.replaceAllObjects(json).waitAll()
            }
        }
    }
}
