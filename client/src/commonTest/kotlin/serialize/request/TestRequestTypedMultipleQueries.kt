package serialize.request

import attributeA
import attributeB
import com.algolia.search.model.internal.request.RequestMultipleQueries
import com.algolia.search.model.internal.request.RequestTypedMultipleQueries
import com.algolia.search.model.multipleindex.FacetIndexQuery
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.KeyDefault
import com.algolia.search.serialize.internal.KeyFacet
import com.algolia.search.serialize.internal.KeyIndexName
import com.algolia.search.serialize.internal.KeyParams
import com.algolia.search.serialize.internal.KeyRequests
import com.algolia.search.serialize.internal.KeyStrategy
import com.algolia.search.serialize.internal.KeyType
import com.algolia.search.serialize.internal.Json
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
                KeyRequests,
                buildJsonArray {
                    add(
                        buildJsonObject {
                            put(KeyIndexName, indexA.raw)
                            put(KeyType, KeyDefault)
                            put(KeyParams, "facets=%5B%22attributeA%22%5D&hitsPerPage=3")
                        }
                    )
                    add(
                        buildJsonObject {
                            put(KeyIndexName, indexB.raw)
                            put(KeyType, KeyFacet)
                            put(KeyFacet, attributeA.raw)
                            put(KeyParams, "facets=%5B%22attributeA%22%2C%22attributeB%22%5D&facetQuery=string")
                        }
                    )
                }
            )
            put(KeyStrategy, MultipleQueriesStrategy.StopIfEnoughMatches.raw)
        }
    }

    @Test
    fun testNoStrategy() {
        val request = RequestMultipleQueries(indexQueries = listOf())
        val serialized = Json.encodeToJsonElement(RequestMultipleQueries, request)

        serialized shouldEqual buildJsonObject { put(KeyRequests, buildJsonArray {}) }
    }
}
