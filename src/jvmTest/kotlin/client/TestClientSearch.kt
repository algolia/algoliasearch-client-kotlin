package client

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.data.IndexQuery
import com.algolia.search.saas.data.Query
import com.algolia.search.saas.query.queryBuilder
import com.algolia.search.saas.query.setFacets
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldNotBeNull


@RunWith(JUnit4::class)
internal class TestClientSearch {

    @Test
    fun search() {
        runBlocking {
            val search = index.search(queryBuilder { setFacets(Attribute("price")) })

            search.facets.shouldNotBeNull()
            search.facetStats.shouldNotBeNull()
        }
    }

    @Test
    fun browse() {
        runBlocking {
            val responseA = index.browse()

            responseA.cursor?.let { index.browse(it) }
        }
    }

    @Test
    fun multiQueries() {
        runBlocking {
            val queries = listOf(
                IndexQuery(index.indexName, Query("a")),
                IndexQuery(index.indexName, Query("b"))
            )
            index.multipleQueries(queries)
        }
    }

    @Test
    fun searchForFacetValue() {
        runBlocking {
            val maxFacetHits = 2
            val response = index.searchForFacetValue(
                Attribute("color"),
                maxFacetHits = maxFacetHits,
                facetQuery = "co",
                query = Query(maxFacetHits = maxFacetHits)
            )

            (response.facetHits.size <= maxFacetHits).shouldBeTrue()
        }
    }
}