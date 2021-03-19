package suite

import clientAdmin1
import com.algolia.search.model.LogType
import runBlocking
import shouldEqual
import kotlin.test.Test

internal class TestSuiteLogs {

    @Test
    fun test() {
        runBlocking {
            clientAdmin1.apply {
                listIndices()
                listIndices()
                val responseLogs = getLogs(hitsPerPage = 2, page = 0, logType = LogType.All)
                responseLogs.logs.size shouldEqual 2
            }
        }
    }
}
