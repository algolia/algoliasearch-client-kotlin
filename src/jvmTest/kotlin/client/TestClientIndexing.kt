package client

import com.algolia.search.saas.client.Index
import com.algolia.search.saas.data.ObjectID
import com.algolia.search.saas.data.PartialUpdate
import com.algolia.search.saas.data.TaskStatus
import com.algolia.search.saas.query.FilterFacet
import com.algolia.search.saas.query.GroupAnd
import com.algolia.search.saas.query.queryBuilder
import com.algolia.search.saas.serialize.KeyObjectId
import com.algolia.search.saas.toAttribute
import com.algolia.search.saas.toObjectID
import io.ktor.client.features.BadResponseStatusException
import io.ktor.http.HttpStatusCode
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
                copyIndex(indexCopyA.indexName).wait().status shouldEqual TaskStatus.Published
            }
            indexCopyA.run {
                clearObjects().wait().status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun suite() {
        runBlocking {
            index.run {
                create(dataCreate)
                replace(dataUpdate)
                updateIncrement(dataUpdate)
                updateDecrement(dataUpdate)
                updateAdd(dataUpdate)
                updateRemove(dataUpdate)
                updateAddUnique(dataUpdate)
                delete(dataCreate)
            }
        }
    }

    @Test
    fun getNotResult() {
        runBlocking {
            index.run {
                getNoResult("404".toObjectID())
            }
        }
    }

    @Test
    fun deleteBy() {
        runBlocking {
            index.run {
                create(dataCreate)
                deleteBy(dataCreate)
                getNoResult(dataCreate.objectID)
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
        getObject(update.objectID, listOf(name)) shouldEqual json {
            name.raw to data.name
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updateIncrement(data: Data) {
        val update = partialUpdateObject(PartialUpdate.Increment(count, 1), data.objectID)

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, listOf(count)) shouldEqual json {
            count.raw to (data.count + 1)
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updateDecrement(data: Data) {
        val update = partialUpdateObject(PartialUpdate.Decrement(count, 1), data.objectID)

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, listOf(count)) shouldEqual json {
            count.raw to data.count
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updateAdd(data: Data) {
        val update = partialUpdateObject(PartialUpdate.Add(brand, samsung), data.objectID)

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, listOf(brand)) shouldEqual json {
            brand.raw to jsonArray {
                +samsung
            }
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updateRemove(data: Data) {
        val update = partialUpdateObject(PartialUpdate.Remove(brand, samsung), data.objectID)

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, listOf(brand)) shouldEqual json {
            brand.raw to jsonArray { }
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.updateAddUnique(data: Data) {
        val update = partialUpdateObject(PartialUpdate.AddUnique(brand, iphone), data.objectID)

        update.wait().status shouldEqual TaskStatus.Published
        getObject(update.objectID, listOf(brand)) shouldEqual json {
            brand.raw to jsonArray { +iphone }
            KeyObjectId to data.objectID.raw
        }
    }

    private suspend fun Index.delete(data: Data) {
        deleteObject(data.objectID).wait().status shouldEqual TaskStatus.Published
    }

    private suspend fun Index.deleteBy(data: Data) {
        val facet = FilterFacet("objectID".toAttribute(), data.objectID.raw)
        val query = queryBuilder {
            filterBuilder.apply { GroupAnd("group") += facet }
        }
        val deleteBy = deleteObjectBy(query)

        deleteBy.wait().status shouldEqual TaskStatus.Published
    }

    private suspend fun Index.getNoResult(objectID: ObjectID) {
        var notFound = false
        try {
            getObject(objectID) shouldEqual null

        } catch (exception: BadResponseStatusException) {
            notFound = exception.statusCode == HttpStatusCode.NotFound
        }
        notFound shouldEqual true
    }
}