package documentation.methods.advanced

import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import kotlinx.serialization.json.json
import runBlocking

@Ignore
internal class DocWait {

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
    fun snippet1() {
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
