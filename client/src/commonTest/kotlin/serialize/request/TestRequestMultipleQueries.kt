package serialize.request

import attributeA
import attributeB
import com.algolia.search.model.internal.request.RequestMultipleQueries
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.Key
import indexA
import indexB
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import shouldEqual
import kotlin.test.Test

internal class TestRequestMultipleQueries {

    @Test
    fun test() {
        val request = RequestMultipleQueries(
            indexQueries = listOf(
                IndexQuery(indexName = indexA, query = Query(facets = setOf(attributeA))),
                IndexQuery(indexName = indexB, query = Query(facets = setOf(attributeA, attributeB)))
            ),
            strategy = MultipleQueriesStrategy.StopIfEnoughMatches
        )
        val serialized = Json.encodeToJsonElement(RequestMultipleQueries, request)

        serialized shouldEqual buildJsonObject {
            put(
                Key.Requests,
                buildJsonArray {
                    add(
                        buildJsonObject {
                            put(Key.IndexName, indexA.raw)
                            put(Key.Params, "facets=%5B%22attributeA%22%5D")
                        }
                    )
                    add(
                        buildJsonObject {
                            put(Key.IndexName, indexB.raw)
                            put(Key.Params, "facets=%5B%22attributeA%22%2C%22attributeB%22%5D")
                        }
                    )
                }
            )
            put(Key.Strategy, MultipleQueriesStrategy.StopIfEnoughMatches.raw)
        }
    }

    @Test
    fun testNoStrategy() {
        val request = RequestMultipleQueries(indexQueries = listOf())
        val serialized = Json.encodeToJsonElement(RequestMultipleQueries, request)

        serialized shouldEqual buildJsonObject { put(Key.Requests, buildJsonArray {}) }
    }
}
