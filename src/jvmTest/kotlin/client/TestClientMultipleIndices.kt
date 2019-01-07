package client

import com.algolia.search.saas.client.Index
import com.algolia.search.saas.data.BatchWrite
import com.algolia.search.saas.data.ObjectID
import com.algolia.search.saas.data.RequestObjects
import com.algolia.search.saas.data.TaskStatus
import com.algolia.search.saas.serialize.KeyObjectId
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.content
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeFalse
import shouldContain
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientMultipleIndices {


    @Test
    fun listIndices() {
        runBlocking {
            client.listIndexes()
        }
    }

    @Test
    fun getObjects() {
        runBlocking {
            val objectID1 = "442854"
            val objectID2 = "322601"
            val objects = client.getObjects(
                RequestObjects(
                    index.indexName,
                    ObjectID(objectID1)
                ),
                RequestObjects(
                    index.indexName,
                    ObjectID(objectID2)
                )
            )

            objects[0].jsonObject[KeyObjectId].content shouldEqual objectID1
            objects[1].jsonObject[KeyObjectId].content shouldEqual objectID2
        }
    }

    @Test
    fun batchWrite() {
        runBlocking {
            index.run {
                copyIndex(copy.indexName).wait().status shouldEqual TaskStatus.Published
            }
            copy.run {
                create(dataCreate)
                replace(dataUpdate)
                update(dataUpdate)
                delete(dataUpdate)
                clear()
                delete()
            }
            client.listIndexes().items.any { it.indexName == copy.indexName }.shouldBeFalse()
        }
    }

    private suspend fun Index.create(data: Data) {
        val create = batchWrite(BatchWrite.AddObject.from(data, Data.serializer()))

        create.objectIDs shouldContain data.objectID
        create.wait().status shouldEqual TaskStatus.Published
    }

    private suspend fun Index.replace(data: Data) {
        val replace = batchWrite(BatchWrite.ReplaceObject.from(data, Data.serializer()))

        replace.objectIDs shouldContain data.objectID
        replace.wait().status shouldEqual TaskStatus.Published
    }

    private suspend fun Index.update(data: Data) {
        val update = batchWrite(BatchWrite.PartialUpdateObject.from(data, Data.serializer()))

        update.objectIDs shouldContain data.objectID
        update.wait().status shouldEqual TaskStatus.Published
    }

    private suspend fun Index.delete(data: Data) {
        val delete = batchWrite(BatchWrite.DeleteObject(data.objectID))

        delete.objectIDs shouldContain data.objectID
        delete.wait().status shouldEqual TaskStatus.Published
    }

    private suspend fun Index.clear() {
        val clear = batchWrite(BatchWrite.ClearIndex)

        clear.wait().status shouldEqual TaskStatus.Published
    }

    private suspend fun Index.delete() {
        val delete = batchWrite(BatchWrite.DeleteIndex)

        delete.wait().status shouldEqual TaskStatus.Published
    }
}