package suite

import clientAdmin1
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.internal.JsonDebug
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonObject
import loadScratch
import shouldEqual
import kotlin.test.Test

internal class TestSuiteBatching {

    private val suffix = "index_batching"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @Test
    fun test() {
        runTest {
            val objects = load(ListSerializer(JsonObject.serializer()), "numbers.json")
            val expected = loadScratch("batches_result.json")
            val batches = load(ListSerializer(BatchOperation), "batches.json")

            index.apply {
                saveObjects(objects).wait() shouldEqual TaskStatus.Published
                batch(batches).wait() shouldEqual TaskStatus.Published
                val hits = browse().hits.map { it.json }

                JsonDebug.encodeToString(ListSerializer(JsonObject.serializer()), hits) shouldEqual expected
            }
        }
    }
}
