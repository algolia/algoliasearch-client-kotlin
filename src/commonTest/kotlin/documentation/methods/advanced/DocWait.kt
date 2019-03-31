package documentation.methods.advanced

import kotlinx.serialization.json.json
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocWait : TestDocumentation() {

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