package suite

import clientAdmin1
import com.algolia.search.helper.toAttribute
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.model.search.Query
import com.algolia.search.model.task.TaskStatus
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import shouldBeTrue
import shouldEqual
import kotlin.test.Test

internal class TestSuiteMultipleOperations {

    private val suffix = "multiple_operations"
    private val indexName1 = testSuiteIndexName(suffix)
    private val indexName2 = indexName1.copy(raw = indexName1.raw + "_dev")
    private val firstname = "firstname"
    private val jimmie = "Jimmie"
    private val json = buildJsonObject { put(firstname, jimmie) }

    @Test
    fun test() {
        runTest {
            val operations = listOf(
                BatchOperationIndex(indexName1, BatchOperation.AddObject(json)),
                BatchOperationIndex(indexName1, BatchOperation.AddObject(json)),
                BatchOperationIndex(indexName2, BatchOperation.AddObject(json)),
                BatchOperationIndex(indexName2, BatchOperation.AddObject(json))
            )

            clientAdmin1.apply {
                val response = multipleBatchObjects(operations)

                response.waitAll().all { it is TaskStatus.Published }.shouldBeTrue()
                val requests = operations.mapIndexed { index, request ->
                    RequestObjects(request.indexName, response.objectIDs[index]!!)
                }

                multipleGetObjects(requests).let {
                    it.results.size shouldEqual 4
                    it.results[0]?.get(firstname)?.let { it.jsonPrimitive.content } shouldEqual jimmie
                    it.results[0]?.get(firstname)?.let { it.jsonPrimitive.content } shouldEqual jimmie
                    it.results[0]?.get(firstname)?.let { it.jsonPrimitive.content } shouldEqual jimmie
                    it.results[0]?.get(firstname)?.let { it.jsonPrimitive.content } shouldEqual jimmie
                }
                val query =
                    Query(query = "", hitsPerPage = 2, facets = setOf("color".toAttribute(), "brand".toAttribute()))
                val indexQueries = listOf(
                    IndexQuery(indexName1, query),
                    IndexQuery(indexName2, query)
                )
                multipleQueries(indexQueries).let {
                    it.results.size shouldEqual 2
                    it.results[0].hits.size shouldEqual 2
                    it.results[1].hits.size shouldEqual 2
                }

                multipleQueries(indexQueries, MultipleQueriesStrategy.StopIfEnoughMatches).let {
                    it.results.size shouldEqual 2
                    it.results[0].hits.size shouldEqual 2
                    it.results[1].hits.size shouldEqual 0
                }
            }
        }
    }
}
