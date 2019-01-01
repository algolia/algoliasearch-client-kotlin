package client

import client.data.IndexName
import client.data.TaskStatus.Published
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientIndices {

    @Test
    fun listCopyAndDelete() {
        runBlocking {
            val destinationName = IndexName("products_android_demo_copy")
            val destination = apiClient.get(destinationName)
            val list = destination.listIndexes()

            if (list.items.any { it.indexName == destinationName }) {
                val delete = destination.run {
                    deleteIndex().wait()
                }
                delete.status shouldEqual Published
            }
            val copy = index.run {
                copyIndex(destinationName).wait()
            }
            copy.status shouldEqual Published

            val delete = destination.run {
                deleteIndex().wait()
            }
            delete.status shouldEqual Published
        }
    }
}