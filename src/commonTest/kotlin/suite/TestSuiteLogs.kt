package suite

import clientAdmin1
import com.algolia.search.model.LogType
import kotlin.test.Test
import runBlocking
import shouldEqual

internal class TestSuiteLogs {

    @Test
    fun test() {
        runBlocking {
            clientAdmin1.apply {
                listIndices()
                listIndices()
                getLogs(hitsPerPage = 2, page = 0, logType = LogType.All).logs.size shouldEqual 2
            }
        }
    }
}
