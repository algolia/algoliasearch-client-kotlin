package client

import client.data.IndexQuery
import client.data.Query
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue


@RunWith(JUnit4::class)
internal class TestClientSearch {

    @Test
    fun search() {
        runBlocking {
            index.search()
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
                IndexQuery(indexName, Query("a")),
                IndexQuery(indexName, Query("b"))
            )
            index.multipleQueries(queries)
        }
    }

    @Test
    fun searchForFacetValue() {
        runBlocking {
            val maxFacetHits = 2
            val response = index.searchForFacetValue(
                "color",
                maxFacetHits = maxFacetHits,
                facetQuery = "co",
                query = Query(maxFacetHits = maxFacetHits)
            )

            (response.facetHits.size <= maxFacetHits).shouldBeTrue()
        }
    }
}