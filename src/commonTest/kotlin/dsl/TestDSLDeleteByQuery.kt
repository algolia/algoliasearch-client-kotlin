package dsl

import attributeA
import com.algolia.search.dsl.deleteByQuery
import com.algolia.search.dsl.facetFilters
import com.algolia.search.dsl.filters
import com.algolia.search.dsl.insideBoundingBox
import com.algolia.search.dsl.insidePolygon
import com.algolia.search.dsl.numericFilters
import com.algolia.search.dsl.tagFilters
import com.algolia.search.helper.and
import com.algolia.search.model.search.BoundingBox
import com.algolia.search.model.search.Polygon
import shouldNotBeNull
import unknown
import kotlin.test.Test

internal class TestDSLDeleteByQuery {

    @Test
    fun filters() {
        val deleteByQuery = deleteByQuery {
            filters {
                and { facet(attributeA, 0) }
            }
        }

        deleteByQuery.filters.shouldNotBeNull()
    }

    @Test
    fun facetFilters() {
        val deleteByQuery = deleteByQuery {
            facetFilters {
                and { facet(attributeA, 0) }
            }
        }

        deleteByQuery.facetFilters.shouldNotBeNull()
    }

    @Test
    fun numericFilters() {
        val deleteByQuery = deleteByQuery {
            numericFilters {
                and { range(attributeA, 0..1) }
            }
        }

        deleteByQuery.numericFilters.shouldNotBeNull()
    }

    @Test
    fun tagFilters() {
        val deleteByQuery = deleteByQuery {
            tagFilters {
                and { tag(unknown) }
            }
        }

        deleteByQuery.tagFilters.shouldNotBeNull()
    }

    @Test
    fun insideBoundingBox() {
        val deleteByQuery = deleteByQuery {
            insideBoundingBox {
                +BoundingBox(0f and 1f, 2f and 3f)
            }
        }

        deleteByQuery.insideBoundingBox.shouldNotBeNull()
    }

    @Test
    fun insidePolygon() {
        val deleteByQuery = deleteByQuery {
            insidePolygon {
                +Polygon(0f and 1f, 2f and 3f, 4f and 5f)
            }
        }

        deleteByQuery.insidePolygon.shouldNotBeNull()
    }
}
