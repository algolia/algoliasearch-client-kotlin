package suite

import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.indexing.PartialUpdate
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.toAttribute
import com.algolia.search.toObjectID
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
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
            .map {
                batches
                    .subList(it * 100, it * 100 + 100)
                    .map { BatchOperation.AddObject.from(Data.serializer(), it) }
            }
    }

    @Before
    fun clean() {
        runBlocking {
            cleanIndex(clientAdmin1, suffix)
        }
    }

    @Test
    fun test() {
        runBlocking {
            index.apply {
                val creations = mutableListOf<Task>()
                val revisions = mutableListOf<Task>()
                val deletions = mutableListOf<Task>()
                val result = saveObject(data).also { creations += it }
                val results = saveObjects(listOf(data, data)).also { creations += it }
                val dataE = Data(result.objectID)
                val dataF = Data(results.objectIDs[0]!!)
                val dataG = Data(results.objectIDs[1]!!)
                val datas = listOf(dataA, dataB, dataC, dataD, dataE, dataF, dataG)

                saveObject(Data.serializer(), dataA).also { creations += it }
                saveObjects(Data.serializer(), listOf(dataB, dataC, dataD)).also { creations += it }
                creations += batchAddObject().map { batch(it) }
                creations.wait().all { it is TaskStatus.Published }.shouldBeTrue()
                datas.forEach { getObject(Data.serializer(), it.objectID) shouldEqual it }
                getObjects(objectIDs).results
                    .filterNotNull()
                    .map { Json.plain.fromJson(Data.serializer(), it) } shouldEqual batches
                browse().nbHits shouldEqual 1007
                revisions += replaceObject(Data.serializer(), updateA)
                revisions += partialUpdateObject(dataE.objectID, PartialUpdate.Increment(attributeValue, 1))
                revisions += replaceObjects(Data.serializer(), listOf(updateB, updateC, updateD))
                revisions += partialUpdateObjects(
                    listOf(
                        dataF.objectID to PartialUpdate.Increment(attributeValue, 1),
                        dataG.objectID to PartialUpdate.Increment(attributeValue, 1)
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