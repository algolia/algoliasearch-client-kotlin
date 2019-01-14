package client

import com.algolia.search.saas.client.Index
import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.TaskStatus.Published
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientIndices {

    private suspend fun deleteIndex(index: Index) {
        index.run {
            deleteIndex().wait().status shouldEqual Published
        }
    }

    private suspend fun deleteIndexIfExists(vararg indexes: Index) {
        val existing = algolia.listIndexes().items.map { it.indexName }

        indexes.forEach { index ->
            if (existing.any { index.indexName == it }) {
                deleteIndex(index)
            }
        }
    }

    private suspend fun copyIndex(index: Index, destination: IndexName) {
        index.run {
            copyIndex(destination).wait().status shouldEqual Published
        }
    }

    private suspend fun moveIndex(index: Index, destination: IndexName) {
        index.run {
            moveIndex(destination).wait().status shouldEqual Published
        }
    }

    @Test
    fun test() {
        runBlocking {
            deleteIndexIfExists(indexCopyA, indexCopyB)
            copyIndex(index, indexCopyA.indexName)
            moveIndex(indexCopyA, indexCopyB.indexName)
            deleteIndex(indexCopyB)
        }
    }
}