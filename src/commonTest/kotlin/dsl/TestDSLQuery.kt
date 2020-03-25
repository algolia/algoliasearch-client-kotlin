package dsl

import attributeA
import com.algolia.search.dsl.alternativesAsExact
import com.algolia.search.dsl.analyticsTags
import com.algolia.search.dsl.attributesToHighlight
import com.algolia.search.dsl.attributesToRetrieve
import com.algolia.search.dsl.attributesToSnippet
import com.algolia.search.dsl.disableExactOnAttributes
import com.algolia.search.dsl.disableTypoToleranceOnAttributes
import com.algolia.search.dsl.explainModules
import com.algolia.search.dsl.facetFilters
import com.algolia.search.dsl.facets
import com.algolia.search.dsl.filters
import com.algolia.search.dsl.insideBoundingBox
import com.algolia.search.dsl.insidePolygon
import com.algolia.search.dsl.numericFilters
import com.algolia.search.dsl.optionalFilters
import com.algolia.search.dsl.optionalWords
import com.algolia.search.dsl.query
import com.algolia.search.dsl.queryLanguages
import com.algolia.search.dsl.responseFields
import com.algolia.search.dsl.restrictSearchableAttributes
import com.algolia.search.dsl.ruleContexts
import com.algolia.search.dsl.tagFilters
import com.algolia.search.helper.and
import com.algolia.search.model.search.BoundingBox
import com.algolia.search.model.search.Polygon
import shouldNotBeNull
import unknown
import kotlin.test.Test

internal class TestDSLQuery {

    @Test
    fun attributesToRetrieve() {
        val query = query {
            attributesToRetrieve { +attributeA }
        }

        query.attributesToRetrieve.shouldNotBeNull()
    }

    @Test
    fun restrictSearchableAttributes() {
        val query = query {
            restrictSearchableAttributes { +attributeA }
        }

        query.restrictSearchableAttributes.shouldNotBeNull()
    }

    @Test
    fun filters() {
        val query = query {
            filters {
                and { facet(attributeA, 0) }
            }
        }

        query.filters!!.isNotEmpty()
    }

    @Test
    fun facetFilters() {
        val query = query {
            facetFilters {
                and { facet(attributeA, 0) }
            }
        }

        query.facetFilters!!.isNotEmpty()
    }

    @Test
    fun numericFilters() {
        val query = query {
            numericFilters {
                and { range(attributeA, 0..1) }
            }
        }

        query.numericFilters!!.isNotEmpty()
    }

    @Test
    fun tagFilters() {
        val query = query {
            tagFilters {
                and { tag(unknown) }
            }
        }

        query.tagFilters!!.isNotEmpty()
    }

    @Test
    fun optionalFilters() {
        val query = query {
            optionalFilters {
                and { facet(attributeA, 0) }
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

    @Test
    fun attributesToHighlight() {
        val query = query {
            attributesToHighlight { +attributeA }
        }

        query.attributesToHighlight.shouldNotBeNull()
    }

    @Test
    fun attributesToSnippet() {
        val query = query {
            attributesToSnippet { +attributeA }
        }

        query.attributesToSnippet.shouldNotBeNull()
    }

    @Test
    fun disableTypoToleranceOnAttributes() {
        val query = query {
            disableTypoToleranceOnAttributes { +attributeA }
        }

        query.disableTypoToleranceOnAttributes.shouldNotBeNull()
    }

    @Test
    fun insideBoundingBox() {
        val query = query {
            insideBoundingBox {
                +BoundingBox(0f and 1f, 2f and 3f)
            }
        }

        query.insideBoundingBox.shouldNotBeNull()
    }

    @Test
    fun insidePolygon() {
        val query = query {
            insidePolygon {
                +Polygon(0f and 1f, 2f and 3f, 4f and 5f)
            }
        }

        query.insidePolygon.shouldNotBeNull()
    }

    @Test
    fun queryLanguages() {
        val query = query {
            queryLanguages { +English }
        }

        query.queryLanguages.shouldNotBeNull()
    }

    @Test
    fun optionalWords() {
        val query = query {
            optionalWords { +unknown }
        }

        query.optionalWords.shouldNotBeNull()
    }

    @Test
    fun disableExactOnAttributes() {
        val query = query {
            disableExactOnAttributes { +unknown }
        }

        query.disableExactOnAttributes.shouldNotBeNull()
    }

    @Test
    fun alternativesAsExact() {
        val query = query {
            alternativesAsExact { +MultiWordsSynonym }
        }

        query.alternativesAsExact.shouldNotBeNull()
    }

    @Test
    fun ruleContexts() {
        val query = query {
            ruleContexts { +unknown }
        }

        query.ruleContexts.shouldNotBeNull()
    }

    @Test
    fun analyticsTags() {
        val query = query {
            analyticsTags { +unknown }
        }

        query.analyticsTags.shouldNotBeNull()
    }

    @Test
    fun responseFields() {
        val query = query {
            responseFields { +All }
        }

        query.responseFields.shouldNotBeNull()
    }

    @Test
    fun explainModules() {
        val query = query {
            explainModules { +MatchAlternatives }
        }

        query.explainModules.shouldNotBeNull()
    }
}
