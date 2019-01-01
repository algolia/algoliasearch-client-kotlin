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

    private val copyName = IndexName("products_android_demo_copy")
    private val copy = client.initIndex(copyName)
    private val destinationName = IndexName("products_android_demo_move")

    private suspend fun deleteIndex(indexName: IndexName) {
        val task = client.deleteIndex(indexName)

        client.wait(indexName, task).status shouldEqual Published
    }

    private suspend fun deleteIndexIfExists(vararg indexNames: IndexName) {
        val existing = client.listIndexes().items.map { it.indexName }

        indexNames.forEach {
            if (existing.contains(it)) {
                deleteIndex(it)
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
            deleteIndexIfExists(copyName, destinationName)
            copyIndex(index, copyName)
            moveIndex(copy, destinationName)
            deleteIndex(destinationName)
        }
    }
}