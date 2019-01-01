package client

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.data.IndexQuery
import com.algolia.search.saas.data.Query
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
            val search = index.search(Query())

            println(search)
            println(search)
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