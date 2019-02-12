package suite

import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.task.TaskStatus
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.list
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSuiteBatching {

    private val suffix = "index_batching"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.getIndex(indexName)
    private val json = Json(encodeDefaults = false, indented = true, indent = "  ")

    @Before
    fun clean() {
        cleanIndex(clientAdmin1, suffix)
    }

    private fun loadBatches(): List<BatchOperation> {
        val string = loadScratch("batches.json").readText()
        val batches = json.parse(BatchOperation.list, string)
        val serialized = json.stringify(BatchOperation.list, batches)

        serialized shouldEqual string
        return batches
    }

    @Test
    fun test() {
        runBlocking {
            val objects = loadFileAsObjects("numbers.json")
            val expected = loadScratch("batches_result.json").readText()
            val batches = loadBatches()

            index.apply {
                saveObjects(objects).wait() shouldEqual TaskStatus.Published
                batch(batches).wait() shouldEqual TaskStatus.Published
                val hits = browse().hits!!.map { it.json }

                json.stringify(JsonObjectSerializer.list, hits) shouldEqual expected
            }
        }
    }
}