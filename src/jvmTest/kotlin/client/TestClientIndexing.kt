package client

import com.algolia.search.saas.client.Index
import com.algolia.search.saas.data.ObjectID
import com.algolia.search.saas.data.PartialUpdate
import com.algolia.search.saas.data.TaskStatus
import com.algolia.search.saas.serialize.KeyObjectId
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
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
                copyIndex(copy.indexName).wait().status shouldEqual TaskStatus.Published
            }
            copy.run {
                clearObjects().wait().status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun updatePartial() {
        runBlocking {
            index.run {
                create(dataCreate)
                replace(dataUpdate)
                updatePartialIncrement(dataUpdate)
                updatePartialDecrement(dataUpdate)
                updatePartialAdd(dataUpdate)
                updatePartialRemove(dataUpdate)
                updatePartialAddUnique(dataUpdate)
                delete(dataCreate.objectID)
            }
        }
    }

    private suspend fun Index.create(data: Data) {
        val create = addObject(data, Data.serializer())

        create.wait().status shouldEqual TaskStatus.Published
        getObject(create.objectID, Data.serializer()) shouldEqual data
    }

    private suspend fun Index.replace(data: Data) {
        val update = replaceObject(data, Data.serializer())

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, name) shouldEqual json {
            name.raw to data.name
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updatePartialIncrement(data: Data) {
        val update = updateObjectPartially(data.objectID, PartialUpdate.Increment(count, 1))

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, count) shouldEqual json {
            count.raw to (data.count + 1)
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updatePartialDecrement(data: Data) {
        val update = updateObjectPartially(data.objectID, PartialUpdate.Decrement(count, 1))

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, count) shouldEqual json {
            count.raw to data.count
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updatePartialAdd(data: Data) {
        val update = updateObjectPartially(data.objectID, PartialUpdate.Add(brands, samsung))

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, brands) shouldEqual json {
            brands.raw to jsonArray {
                +iphone
                +samsung
            }
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updatePartialRemove(data: Data) {
        val update = updateObjectPartially(data.objectID, PartialUpdate.Remove(brands, samsung))

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, brands) shouldEqual json {
            brands.raw to jsonArray { +iphone }
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updatePartialAddUnique(data: Data) {
        val update = updateObjectPartially(data.objectID, PartialUpdate.AddUnique(brands, iphone))

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, brands) shouldEqual json {
            brands.raw to jsonArray { +iphone }
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.delete(objectID: ObjectID) {
        deleteObject(objectID).wait().status shouldEqual TaskStatus.Published
    }
}