package suite

import com.algolia.search.model.LogType
import com.algolia.search.toIndexName
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSuiteLogs {

    @Test
    fun test() {
        runBlocking {
            clientAdmin1.apply {
                listIndexes()
                listIndexes()
                getLogs(length = 2, offset = 0, logType = LogType.All).logs.size shouldEqual 2
                initIndex("products_android_demo".toIndexName()).getLogs()
            }
        }
    }
}