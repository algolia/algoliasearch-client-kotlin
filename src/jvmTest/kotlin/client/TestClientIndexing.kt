package client

import com.algolia.search.saas.data.TaskStatus
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientIndexing {

    @Test
    fun clearObjects() {
        runBlocking {
            index.run {
                copyIndex(indexCopyA.indexName).wait().status shouldEqual TaskStatus.Published
            }
            indexCopyA.run {
                clearObjects().wait().status shouldEqual TaskStatus.Published
            }
        }
    }
}