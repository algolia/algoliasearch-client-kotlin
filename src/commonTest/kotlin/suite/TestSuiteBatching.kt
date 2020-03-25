package suite

import clientAdmin1
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.JsonDebug
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.list
import loadScratch
import runBlocking
import shouldEqual
import kotlin.test.Test

internal class TestSuiteBatching {

    private val suffix = "index_batching"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @Test
    fun test() {
        runBlocking {
            val objects = load(JsonObjectSerializer.list, "numbers.json")
            val expected = loadScratch("batches_result.json")
            val batches = load(BatchOperation.list, "batches.json")

            index.apply {
                saveObjects(objects).wait() shouldEqual TaskStatus.Published
                batch(batches).wait() shouldEqual TaskStatus.Published
                val hits = browse().hits.map { it.json }

                JsonDebug.stringify(JsonObjectSerializer.list, hits) shouldEqual expected
            }
        }
    }
}
