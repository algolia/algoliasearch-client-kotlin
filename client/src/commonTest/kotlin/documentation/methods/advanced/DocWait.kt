package documentation.methods.advanced

import documentation.index
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Ignore
import kotlin.test.Test

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
        runTest {
            val json = buildJsonObject {
                put("Firstname", "Jimmie")
                put("Lastname", "Barninger")
            }

            index.apply {
                saveObject(json).wait()
            }
        }
    }
}
