package client

import com.algolia.search.saas.model.Attribute
import com.algolia.search.saas.model.search.Query
import com.algolia.search.saas.query.queryBuilder
import com.algolia.search.saas.query.setFacets
import com.algolia.search.saas.toAttribute
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldContainKey
import shouldNotBeNull


@RunWith(JUnit4::class)
internal class TestClientSearch {

    @Test
    fun search() {
        runBlocking {
            val price = "price".toAttribute()
            val search = index.search(queryBuilder { setFacets(price) })

            search.facets.shouldNotBeNull()
            search.facets shouldContainKey price
            search.facetStats.shouldNotBeNull()
            search.facetStats shouldContainKey price
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