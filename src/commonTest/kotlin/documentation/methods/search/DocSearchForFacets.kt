package documentation.methods.search

import com.algolia.search.dsl.requestOptions
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.FacetQuery
import com.algolia.search.model.search.Query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocSearchForFacets {

//    suspend fun Index.searchForFacets(
//        #{attribute}: __Attribute__,
//        #{query}: __FacetQuery__ = FacetQuery(),
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseSearchForFacetValues
//
//    data class FacetQuery(
//        var #{facetQuery}: __String__? = null,
//        var #{query}: __Query?__ = null,
//    )
//
//    // any #{searchParameters} can be set on the Query object

    @Test
    fun snippet1() {
        runBlocking {
            val attribute = Attribute("category")
            val query = FacetQuery(facetQuery = "phone")

            index.searchForFacets(attribute, query)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val attribute = Attribute("category")
            val query = FacetQuery(
                facetQuery = "phone",
                query = Query(filters = "brand:Apple")
            )

            index.searchForFacets(attribute, query)
        }
    }

    @Test
    fun snippet3() {
        runBlocking {
            val attribute = Attribute("category")
            val query = FacetQuery(
                facetQuery = "phone",
                query = Query(filters = "brand:Apple")
            )
            val requestOptions = requestOptions {
                header("X-Algolia-User-ID", "user123")
            }

            index.searchForFacets(attribute, query, requestOptions)
        }
    }
}