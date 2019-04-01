package documentation.methods.search

import com.algolia.search.helper.requestOptionsBuilder
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.FacetValuesQuery
import com.algolia.search.model.search.Query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocSearchForFacetValues {

//    suspend fun Index.searchForFacets(
//        #{attribute}: __Attribute__,
//        #{query}: __FacetValuesQuery__ = FacetValuesQuery(),
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseSearchForFacetValues
//
//    data class FacetValuesQuery(
//        var #{facetQuery}: __String__? = null,
//        var #{query}: __Query?__ = null,
//    )
//
//    // any #{searchParameters} can be set on the Query object

    @Test
    fun example() {
        runBlocking {
            val attribute = Attribute("category")
            val query = FacetValuesQuery(facetQuery = "phone")

            index.searchForFacets(attribute, query)
        }
    }

    @Test
    fun exampleAdditional() {
        runBlocking {
            val attribute = Attribute("category")
            val query = FacetValuesQuery(
                facetQuery = "phone",
                query = Query(filters = "brand:Apple")
            )

            index.searchForFacets(attribute, query)
        }
    }

    @Test
    fun exampleExtraHeader() {
        runBlocking {
            val attribute = Attribute("category")
            val query = FacetValuesQuery(
                facetQuery = "phone",
                query = Query(filters = "brand:Apple")
            )
            val requestOptions = requestOptionsBuilder {
                header("X-Algolia-User-ID", "user123")
            }

            index.searchForFacets(attribute, query, requestOptions)
        }
    }
}