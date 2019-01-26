package client

import com.algolia.search.client.Index
import com.algolia.search.model.IndexName
import com.algolia.search.model.common.TaskStatus
import com.algolia.search.model.common.TaskStatus.Published
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientIndex {

    private suspend fun deleteIndex(index: Index) {
        index.run {
            deleteIndex().wait() shouldEqual Published
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
            copyIndex(destination).wait() shouldEqual Published
        }
    }

    private suspend fun moveIndex(index: Index, destination: IndexName) {
        index.run {
            moveIndex(destination).wait() shouldEqual Published
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

    @Test
    fun clear() {
        runBlocking {
            index.run {
                copyIndex(indexCopyA.indexName).wait() shouldEqual TaskStatus.Published
            }
            indexCopyA.run {
                clear().wait() shouldEqual TaskStatus.Published
            }
        }
    }
}