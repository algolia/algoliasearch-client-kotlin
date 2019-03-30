package dsl

import attributeA
import com.algolia.search.dsl.*
import shouldNotBeEmpty
import unknown
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
            restrictSearchableAttributes { +attributeA }
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
    fun facetFilters() {
        val query = query {
            facetFilters {
                and { +facet(attributeA, 0) }
            }
        }

        query.facetFilters!!.isNotEmpty()
    }

    @Test
    fun numericFilters() {
        val query = query {
            numericFilters {
                and { +range(attributeA, 0..1) }
            }
        }

        query.numericFilters!!.isNotEmpty()
    }

    @Test
    fun tagFilters() {
        val query = query {
            tagFilters {
                and { +tag(unknown) }
            }
        }

        query.tagFilters!!.isNotEmpty()
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

    @Test
    fun facets() {
        val query = query {
            facets { +attributeA }
        }

        query.facets!!.isNotEmpty()
    }
}