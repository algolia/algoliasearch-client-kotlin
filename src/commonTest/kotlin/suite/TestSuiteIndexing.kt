package suite

import clientAdmin1
import com.algolia.search.helper.toAttribute
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.indexing.Partial
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.Json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.json
import runBlocking
import shouldBeTrue
import shouldEqual
import kotlin.test.AfterTest
import kotlin.test.Test


internal class TestSuiteIndexing {

    @Serializable
    data class Data(
        override val objectID: ObjectID,
        val value: Int = 0
    ) : Indexable

    private val suffix = "indexing"
    private val attributeValue = "value".toAttribute()
    private val dataA = Data("A".toObjectID())
    private val dataB = Data("B".toObjectID())
    private val dataC = Data("C".toObjectID())
    private val dataD = Data("D".toObjectID())
    private val data = json { attributeValue.raw to 0 }
    private val batches = (0 until 1000).map { Data(it.toString().toObjectID()) }
    private val objectIDs = batches.map { it.objectID }
    private val updateA = dataA.copy(value = 1)
    private val updateB = dataB.copy(value = 1)
    private val updateC = dataC.copy(value = 1)
    private val updateD = dataD.copy(value = 1)
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)


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
        runBlocking {
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
                    .map { Json.fromJson(Data.serializer(), it) } shouldEqual batches
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
}