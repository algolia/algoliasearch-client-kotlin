package suite

import clientAdmin1
import com.algolia.search.model.LogType
import runBlocking
import shouldBeEmpty
import shouldEqual
import snippets.index
import kotlin.test.Test


internal class TestSuiteLogs {

    @Test
    fun test() {
        runBlocking {
            clientAdmin1.apply {
                listIndices()
                listIndices()
                getLogs(page = 2, hitsPerPage = 0, logType = LogType.All).logs.size shouldEqual 2
                index.getLogs().logs.shouldBeEmpty()
            }
        }
    }
}