package serialize.request

import attributeA
import attributeB
import com.algolia.search.model.internal.request.RequestMultipleQueries
import com.algolia.search.model.internal.request.RequestTypedMultipleQueries
import com.algolia.search.model.multipleindex.FacetIndexQuery
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
import string
import kotlin.test.Test

class TestRequestTypedMultipleQueries {

    @Test
    fun test() {
        val request = RequestTypedMultipleQueries(
            requests = listOf(
                IndexQuery(indexName = indexA, query = Query(facets = setOf(attributeA), hitsPerPage = 3)),
                FacetIndexQuery(
                    indexName = indexB,
                    query = Query(facets = setOf(attributeA, attributeB)),
                    facetAttribute = attributeA,
                    facetQuery = string
                )
            ),
            strategy = MultipleQueriesStrategy.StopIfEnoughMatches
        )
        val serialized = Json.encodeToJsonElement(RequestTypedMultipleQueries, request)

        serialized shouldEqual buildJsonObject {
            put(
                Key.Requests,
                buildJsonArray {
                    add(
                        buildJsonObject {
                            put(Key.IndexName, indexA.raw)
                            put(Key.Type, Key.Default)
                            put(Key.Params, "facets=%5B%22attributeA%22%5D&hitsPerPage=3")
                        }
                    )
                    add(
                        buildJsonObject {
                            put(Key.IndexName, indexB.raw)
                            put(Key.Type, Key.Facet)
                            put(Key.Facet, attributeA.raw)
                            put(Key.Params, "facets=%5B%22attributeA%22%2C%22attributeB%22%5D&facetQuery=string")
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
