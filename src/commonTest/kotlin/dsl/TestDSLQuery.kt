package dsl

import attributeA
import com.algolia.search.dsl.*
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
                and { +facet(attributeA, 0) }
            }
        }

        query.filters!!.isNotEmpty()
    }

    @Test
    fun optionalFilters() {
        val query = query {
            optionalFilters {
                and { +facet(attributeA, 0) }
            }
        }

        query.optionalFilters!!.isNotEmpty()
    }
}