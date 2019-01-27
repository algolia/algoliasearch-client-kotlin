package client

import com.algolia.search.client.Index
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.model.task.TaskStatus
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
                copyIndex(indexCopyA.indexName).wait() shouldEqual TaskStatus.Published
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
            val operations = listOf(
                BatchOperationIndex(
                    index.indexName,
                    BatchOperation.AddObject.from(dataCreate, Data.serializer())
                ),
                BatchOperationIndex(
                    index.indexName,
                    BatchOperation.DeleteObject(dataCreate.objectID)
                )
            )
            val batch = algolia.multipleBatchObjects(operations)
            algolia.waitAll(batch.taskIDs).forEach { it shouldEqual TaskStatus.Published }
        }
    }

    private suspend fun Index.create(data: Data) {
        val create = batch(listOf(BatchOperation.AddObject.from(data, Data.serializer())))

        create.objectIDs shouldContain data.objectID
        create.wait() shouldEqual TaskStatus.Published
    }

    private suspend fun Index.replace(data: Data) {
        val replace = batch(listOf(BatchOperation.ReplaceObject.from(data, Data.serializer())))

        replace.objectIDs shouldContain data.objectID
        replace.wait() shouldEqual TaskStatus.Published
    }

    private suspend fun Index.update(data: Data) {
        val update = batch(listOf(BatchOperation.UpdateObject.from(data, Data.serializer())))

        update.objectIDs shouldContain data.objectID
        update.wait() shouldEqual TaskStatus.Published
    }

    private suspend fun Index.delete(data: Data) {
        val delete = batch(listOf(BatchOperation.DeleteObject(data.objectID)))

        delete.objectIDs shouldContain data.objectID
        delete.wait() shouldEqual TaskStatus.Published
    }

    private suspend fun Index.clear() {
        val clear = batch(listOf(BatchOperation.ClearIndex))

        clear.wait() shouldEqual TaskStatus.Published
    }

    private suspend fun Index.delete() {
        val delete = batch(listOf(BatchOperation.DeleteIndex))

        delete.wait() shouldEqual TaskStatus.Published
    }
}