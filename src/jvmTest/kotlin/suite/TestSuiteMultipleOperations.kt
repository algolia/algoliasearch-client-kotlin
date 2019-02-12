package suite

import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.model.search.Query
import com.algolia.search.model.task.TaskStatus
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSuiteMultipleOperations {

    private val suffix = "multiple_operations"
    private val indexName1 = testSuiteIndexName(suffix)
    private val indexName2 = indexName1.copy(raw = indexName1.raw + "_dev")
    private val firstname = "firstname"
    private val jimmie = "Jimmie"
    private val json = json { firstname to jimmie }

    @Before
    fun clean() {
        cleanIndex(clientAdmin1, suffix)
    }

    @Test
    fun test() {
        runBlocking {
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
                    RequestObjects(request.indexName, response.objectIDs!![index]!!)
                }

                multipleGetObjects(requests).let {
                    it.results.size shouldEqual 4
                    it.results[0]?.get(firstname)?.content shouldEqual jimmie
                    it.results[0]?.get(firstname)?.content shouldEqual jimmie
                    it.results[0]?.get(firstname)?.content shouldEqual jimmie
                    it.results[0]?.get(firstname)?.content shouldEqual jimmie
                }
                val indexQueries = listOf(
                    IndexQuery(indexName1, Query(query = "", hitsPerPage = 2)),
                    IndexQuery(indexName2, Query(query = "", hitsPerPage = 2))
                )
                multipleQueries(indexQueries).let {
                    it.results.size shouldEqual 2
                    it.results[0].hits?.size shouldEqual 2
                    it.results[1].hits?.size shouldEqual 2
                }

                multipleQueries(indexQueries, MultipleQueriesStrategy.StopIfEnoughMatches).let {
                    it.results.size shouldEqual 2
                    it.results[0].hits?.size shouldEqual 2
                    it.results[1].hits?.size shouldEqual 0
                }
            }
        }
    }
}