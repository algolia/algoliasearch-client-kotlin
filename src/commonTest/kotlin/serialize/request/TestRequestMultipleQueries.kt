package serialize.request

import attributeA
import attributeB
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.request.RequestMultipleQueries
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyRequests
import com.algolia.search.serialize.KeyStrategy
import indexA
import indexB
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import shouldEqual
import kotlin.test.Test


internal class TestRequestMultipleQueries {

    @Test
    fun test() {
        val request = RequestMultipleQueries(
            indexQueries = listOf(
                IndexQuery(indexName = indexA, query = Query(facets = listOf(attributeA))),
                IndexQuery(indexName = indexB, query = Query(facets = listOf(attributeA, attributeB)))
            ),
            strategy = MultipleQueriesStrategy.StopIfEnoughMatches
        )
        val serialized = Json.plain.toJson(RequestMultipleQueries, request)

        serialized shouldEqual json {
            KeyRequests to jsonArray {
                +json {
                    KeyIndexName to indexA.raw
                    KeyParams to "facets=%5B%22attributeA%22%5D"
                }
                +json {
                    KeyIndexName to indexB.raw
                    KeyParams to "facets=%5B%22attributeA%22%2C%22attributeB%22%5D"
                }
            }
            KeyStrategy to MultipleQueriesStrategy.StopIfEnoughMatches.raw
        }
    }

    @Test
    fun testNoStrategy() {
        val request = RequestMultipleQueries(indexQueries = listOf())
        val serialized = Json.plain.toJson(RequestMultipleQueries, request)

        serialized shouldEqual json { KeyRequests to jsonArray {} }
    }
}