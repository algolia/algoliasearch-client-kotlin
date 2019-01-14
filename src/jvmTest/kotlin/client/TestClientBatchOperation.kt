package client

import com.algolia.search.saas.client.Index
import com.algolia.search.saas.data.BatchOperation
import com.algolia.search.saas.data.BatchOperationIndex
import com.algolia.search.saas.data.TaskStatus
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeFalse
import shouldContain
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientBatchOperation {

    @Test
    fun batch() {
        runBlocking {
            index.run {
                create(dataCreate)
                replace(dataUpdate)
                update(dataUpdate)
                delete(dataUpdate)
            }
            algolia.listIndexes().items.any { it.indexName == indexCopyA.indexName }.shouldBeFalse()
        }
    }

    @Test
    fun batchDelete() {
        runBlocking {
            index.run {
                copyIndex(indexCopyA.indexName).wait().status shouldEqual TaskStatus.Published
            }
            indexCopyA.run {
                clear()
                delete()
            }
        }
    }

    @Test
    fun batchIndex() {
        runBlocking {
            val batch = algolia.batch(
                BatchOperationIndex(index.indexName, BatchOperation.AddObject.from(dataCreate, Data.serializer())),
                BatchOperationIndex(index.indexName, BatchOperation.DeleteObject(dataCreate.objectID))
            )
            algolia.waitAll(batch.taskIDs).forEach { it.status shouldEqual TaskStatus.Published }
        }
    }

    private suspend fun Index.create(data: Data) {
        val create = batch(BatchOperation.AddObject.from(data, Data.serializer()))

        create.objectIDs shouldContain data.objectID
        create.wait().status shouldEqual TaskStatus.Published
    }

    private suspend fun Index.replace(data: Data) {
        val replace = batch(BatchOperation.ReplaceObject.from(data, Data.serializer()))

        replace.objectIDs shouldContain data.objectID
        replace.wait().status shouldEqual TaskStatus.Published
    }

    private suspend fun Index.update(data: Data) {
        val update = batch(BatchOperation.UpdateObject.from(data, Data.serializer()))

        update.objectIDs shouldContain data.objectID
        update.wait().status shouldEqual TaskStatus.Published
    }

    private suspend fun Index.delete(data: Data) {
        val delete = batch(BatchOperation.DeleteObject(data.objectID))

        delete.objectIDs shouldContain data.objectID
        delete.wait().status shouldEqual TaskStatus.Published
    }

    private suspend fun Index.clear() {
        val clear = batch(BatchOperation.ClearIndex)

        clear.wait().status shouldEqual TaskStatus.Published
    }

    private suspend fun Index.delete() {
        val delete = batch(BatchOperation.DeleteIndex)

        delete.wait().status shouldEqual TaskStatus.Published
    }
}