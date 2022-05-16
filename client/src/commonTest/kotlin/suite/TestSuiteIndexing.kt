package suite

import clientAdmin1
import com.algolia.search.helper.toAttribute
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.DeleteByQuery
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.indexing.Partial
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.internal.Json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import shouldBeTrue
import shouldEqual
import kotlin.test.Test

internal class TestSuiteIndexing {

    private val suffix = "indexing"
    private val attributeValue = "value".toAttribute()
    private val dataA = Data("A".toObjectID())
    private val dataB = Data("B".toObjectID())
    private val dataC = Data("C".toObjectID())
    private val dataD = Data("D".toObjectID())
    private val data = buildJsonObject { put(attributeValue.raw, 0) }
    private val batches = (0 until 1000).map { Data(it.toString().toObjectID()) }
    private val objectIDs = batches.map { it.objectID }
    private val updateA = dataA.copy(value = 1)
    private val updateB = dataB.copy(value = 1)
    private val updateC = dataC.copy(value = 1)
    private val updateD = dataD.copy(value = 1)
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @Serializable
    data class Data(
        override val objectID: ObjectID,
        val value: Int = 0,
        @SerialName("_tags") val tags: List<String>? = null,
    ) : Indexable

    private fun batchAddObject(): List<List<BatchOperation.AddObject>> {
        return (0 until 10)
            .map { index ->
                batches
                    .subList(index * 100, index * 100 + 100)
                    .map { BatchOperation.AddObject.from(Data.serializer(), it) }
            }
    }

    @Test
    fun test() {
        runTest {
            index.apply {
                val creations = mutableListOf<Task>()
                val revisions = mutableListOf<Task>()
                val deletions = mutableListOf<Task>()
                val response = saveObject(data).also { creations += it }
                val responses = saveObjects(listOf(data, data)).also { creations += it }
                val dataE = Data(response.objectID)
                val dataF = Data(responses.objectIDs[0]!!)
                val dataG = Data(responses.objectIDs[1]!!)
                val datas = listOf(dataA, dataB, dataC, dataD, dataE, dataF, dataG)

                saveObject(Data.serializer(), dataA).also { creations += it }
                saveObjects(Data.serializer(), listOf(dataB, dataC, dataD)).also { creations += it }
                creations += batchAddObject().map { batch(it) }
                creations.wait().all { it is TaskStatus.Published }.shouldBeTrue()
                datas.forEach { getObject(Data.serializer(), it.objectID) shouldEqual it }
                getObjects(objectIDs).results
                    .filterNotNull()
                    .map { Json.decodeFromJsonElement(Data.serializer(), it) } shouldEqual batches
                browse().nbHits shouldEqual 1007
                revisions += replaceObject(Data.serializer(), updateA)
                revisions += partialUpdateObject(dataE.objectID, Partial.Increment(attributeValue, 1))
                revisions += replaceObjects(Data.serializer(), listOf(updateB, updateC, updateD))
                revisions += partialUpdateObjects(
                    listOf(
                        dataF.objectID to Partial.Increment(attributeValue, 1),
                        dataG.objectID to Partial.Increment(attributeValue, 1)
                    )
                )
                revisions.wait().all { it is TaskStatus.Published }.shouldBeTrue()
                datas.forEach { getObject(Data.serializer(), it.objectID) shouldEqual it.copy(value = 1) }
                deletions += datas.map { deleteObject(it.objectID) }
                deletions += clearObjects()
                deletions.wait().all { it is TaskStatus.Published }.shouldBeTrue()
                browse().nbHits shouldEqual 0
            }
        }
    }

    @Test
    fun testDeleteBy() {
        runTest {
            index.apply {
                // Create and save 10 tagged Data objects
                val tagValue = "algolia"
                val objectsToBatch = (1..10).map { Data(objectID = ObjectID("$it"), tags = listOf(tagValue)) }.toList()
                saveObjects(Data.serializer(), objectsToBatch).wait()

                // Delete one Data object by its ID. The index content should be 9.
                deleteObject(objectsToBatch[0].objectID).wait()
                browse().nbHits shouldEqual objectsToBatch.size - 1

                // Delete the rest of the data objects by tag. The index should become empty.
                deleteObjectsBy(DeleteByQuery(tagFilters = listOf(listOf(tagValue)))).wait()
                browse().nbHits shouldEqual 0
            }
        }
    }
}
