package documentation.methods.search

import com.algolia.search.dsl.requestOptions
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocSearchForFacets {

//    suspend fun Index.searchForFacets(
//        #{attribute}: __Attribute__,
//        #{facetQuery}: __String?__ = null,
//        [query](#searchParameters): __Query?__ = Query(),
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseSearchForFacetValues

    @Test
    fun snippet1() {
        runTest {
            val attribute = Attribute("category")

            index.searchForFacets(attribute, "phone")
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val attribute = Attribute("category")

            index.searchForFacets(attribute, "phone", Query(filters = "brand:Apple"))
        }
    }

    @Test
    fun snippet3() {
        runTest {
            val attribute = Attribute("category")
            val requestOptions = requestOptions {
                header("X-Algolia-User-ID", "user123")
            }

            index.searchForFacets(attribute, "phone", Query(filters = "brand:Apple"), requestOptions)
        }
    }
}
