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
    private val indexName = testSuiteIndexName("indexing")

    private fun batchAddObject(): List<List<BatchOperation.AddObject>> {
        return (0 until 10)
            .map {
                batches
                    .subList(it * 100, it * 100 + 100)
                    .map { BatchOperation.AddObject.from(it, Data.serializer()) }
            }
    }

    @Before
    fun clean() {
        cleanIndex("indexing")
    }

    @Test
    fun suite() {
        runBlocking {
            val index = clientAdmin1.getIndex(indexName)

            index.apply {
                val creations = mutableListOf<Task>()
                val revisions = mutableListOf<Task>()
                val deletions = mutableListOf<Task>()
                val result = addObject(data).also { creations += it }
                val results = addObjects(listOf(data, data)).also { creations += it }
                val dataE = Data(result.objectID)
                val dataF = Data(results.objectIDs[0]!!)
                val dataG = Data(results.objectIDs[1]!!)
                val datas = listOf(dataA, dataB, dataC, dataD, dataE, dataF, dataG)

                addObject(dataA, Data.serializer()).also { creations += it }
                addObjects(listOf(dataB, dataC, dataD), Data.serializer()).also { creations += it }
                creations += batchAddObject().map { batch(it) }
                creations.wait().all { it is TaskStatus.Published }.shouldBeTrue()
                datas.forEach { getObject(it.objectID, Data.serializer()) shouldEqual it }
                getObjects(objectIDs).results
                    .filterNotNull()
                    .map { Json.plain.fromJson(Data.serializer(), it) } shouldEqual batches
                browse().nbHits shouldEqual 1007
                revisions += updateObject(updateA, Data.serializer())
                revisions += partialUpdateObject(PartialUpdate.Increment(attributeValue, 1), dataE.objectID)
                revisions += updateObjects(listOf(updateB, updateC, updateD), Data.serializer())
                revisions += partialUpdateObjects(
                    listOf(
                        PartialUpdate.Increment(attributeValue, 1) to dataF.objectID,
                        PartialUpdate.Increment(attributeValue, 1) to dataG.objectID
                    )
                )
                revisions.wait().all { it is TaskStatus.Published }.shouldBeTrue()
                datas.forEach { getObject(it.objectID, Data.serializer()) shouldEqual it.copy(value = 1) }
                deletions += datas.map { deleteObject(it.objectID) }
                deletions += deleteObjects(batches.map { it.objectID })
                deletions.wait().all { it is TaskStatus.Published }.shouldBeTrue()
                browse().nbHits shouldEqual 0
            }
        }
    }
}