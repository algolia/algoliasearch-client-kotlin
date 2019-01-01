package client

import client.client.Index
import client.data.IndexName
import client.data.TaskStatus.Published
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientIndices {

    private val copy = client.initIndex(IndexName("products_android_demo_copy"))
    private val destination = client.initIndex(IndexName("products_android_demo_move"))

    private suspend fun deleteIndex(index: Index) {
        index.run {
            index.deleteIndex().wait().status shouldEqual Published
        }
    }

    private suspend fun deleteIndexIfExists(vararg indexes: Index) {
        val existing = client.listIndexes().items.map { it.indexName }

        indexes.forEach { index ->
            if (existing.any { index.indexName == it }) {
                deleteIndex(index)
            }
        }
    }

    private suspend fun copyIndex(index: Index, destination: IndexName) {
        index.run {
            index.copyIndex(destination).wait().status shouldEqual Published
        }
    }

    private suspend fun moveIndex(index: Index, destination: IndexName) {
        index.run {
            index.moveIndex(destination).wait().status shouldEqual Published
        }
    }

    @Test
    fun test() {
        runBlocking {
            deleteIndexIfExists(copy, destination)
            copyIndex(index, copy.indexName)
            moveIndex(copy, destination.indexName)
            deleteIndex(destination)
        }
    }
}