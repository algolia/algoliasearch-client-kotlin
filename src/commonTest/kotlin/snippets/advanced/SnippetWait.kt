package snippets.advanced

import kotlinx.serialization.json.json
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetWait : TestSnippets() {

//    suspend fun waitTask(
//        #{taskID}: __TaskID__,
//        timeout: __Long?__ = null,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): TaskStatus
//
//    suspend fun Task.wait(
//        timeout: __Long?__ = null,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): TaskStatus
//
//    suspend fun List<Task>.wait(
//        timeout: __Long?__ = null,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): List<TaskStatus>

    @Test
    fun waitOperation() {
        runBlocking {
            val json = json {
                "Firstname" to "Jimmie"
                "Lastname" to "Barninger"
            }

            index.apply {
                saveObject(json).wait()
            }
        }
    }
}