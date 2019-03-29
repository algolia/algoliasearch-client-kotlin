package dsl

import attributeA
import com.algolia.search.dsl.*
import com.algolia.search.filter.FilterFacet
import com.algolia.search.filter.GroupAnd
import shouldNotBeEmpty
import kotlin.test.Test


internal class TestDSLQuery {

    @Test
    fun attributesToRetrieve() {
        val query = query {
            attributesToRetrieve { +attributeA }
        }

        query.attributesToRetrieve!!.shouldNotBeEmpty()
    }

    @Test
    fun restrictSearchableAttributes() {
        val query = query {
            restrictSearchableAttributes {
                +attributeA
            }
        }

        query.restrictSearchableAttributes!!.shouldNotBeEmpty()
    }

    @Test
    fun filters() {
        val query = query {
            filters {
                GroupAnd("and") += FilterFacet(attributeA, 0)
            }
        }

        query.filters!!.isNotEmpty()
    }

    @Test
    fun optionalFilters() {
        val query = query {
            optionalFilters {
                GroupAnd("and") += FilterFacet(attributeA, 0)
            }
        }

        query.optionalFilters!!.isNotEmpty()
    }
}