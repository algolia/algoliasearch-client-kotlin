package suite

import clientAdmin1
import com.algolia.search.helper.toIndexName
import com.algolia.search.model.LogType
import runBlocking
import shouldBeEmpty
import shouldEqual
import kotlin.test.Test


internal class TestSuiteLogs {

    @Test
    fun test() {
        runBlocking {
            clientAdmin1.apply {
                listIndices()
                listIndices()
                getLogs(length = 2, offset = 0, logType = LogType.All).logs.size shouldEqual 2
                initIndex("products_android_demo".toIndexName()).getLogs().logs.shouldBeEmpty()
            }
        }
    }
}