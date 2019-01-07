package client

import com.algolia.search.saas.client.Index
import com.algolia.search.saas.data.*
import com.algolia.search.saas.serialize.KeyObjectId
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientIndexing {

    @Serializable
    private data class Data(
        val name: String,
        val count: Int,
        val brands: List<String>,
        override val objectID: ObjectID
    ) : Indexable

    private val name = Attribute("name")
    private val count = Attribute("count")
    private val brands = Attribute("brands")
    private val iphone = "iPhone"
    private val samsung = "Samsung"
    private val dataCreate = Data("Phone", 1000, listOf(iphone), ObjectID("test_suite"))
    private val dataUpdate = dataCreate.copy(name = "Telephone")

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
    fun suite() {
        runBlocking {
            index.run {
                create(dataCreate)
                update(dataUpdate)
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

    private suspend fun Index.update(data: Data) {
        val update = updateObject(data.objectID, Data.serializer(), data)

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, name) shouldEqual json {
            name.raw to data.name
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updatePartialIncrement(data: Data) {
        val update = updateObjectPartially(data.objectID, UpdateOperation.Increment(count, 1))

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, count) shouldEqual json {
            count.raw to (data.count + 1)
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updatePartialDecrement(data: Data) {
        val update = updateObjectPartially(data.objectID, UpdateOperation.Decrement(count, 1))

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, count) shouldEqual json {
            count.raw to data.count
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updatePartialAdd(data: Data) {
        val update = updateObjectPartially(data.objectID, UpdateOperation.Add(brands, samsung))

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
        val update = updateObjectPartially(data.objectID, UpdateOperation.Remove(brands, samsung))

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, brands) shouldEqual json {
            brands.raw to jsonArray { +iphone }
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updatePartialAddUnique(data: Data) {
        val update = updateObjectPartially(data.objectID, UpdateOperation.AddUnique(brands, iphone))

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