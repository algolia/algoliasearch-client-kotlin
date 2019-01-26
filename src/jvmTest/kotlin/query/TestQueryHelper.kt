package query

import buildTest
import com.algolia.search.model.*
import com.algolia.search.model.enums.AlternativesAsExact
import com.algolia.search.model.enums.QueryLanguage
import com.algolia.search.model.enums.ResponseFields
import com.algolia.search.model.search.Query
import com.algolia.search.query.*
import com.algolia.search.to
import facetA
import facetB
import groupOrA
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestQueryHelper {

    private val attributeA = Attribute("attributeA")
    private val attributeB = Attribute("attributeB")

    @Test
    fun testClone() {
        val query = Query()

        query.clone().also {
            it.filters shouldEqual null
            it.optionalFilters shouldEqual null
        }
        query.filterBuilder.apply {
            groupOrA += facetA
        }
        query.optionalFilterBuilder.apply {
            groupOrA += facetA
        }
        query.clone().also {
            it.filters shouldEqual query.filterBuilder.build()
            it.optionalFilters shouldEqual query.optionalFilterBuilder.build()
        }
        query.filters = "test"
        query.optionalFilters = listOf()
        query.clone().also {
            it.filters shouldEqual "test"
            it.optionalFilters shouldEqual listOf()
        }
    }

    @Test
    fun attributesToRetrieve() {
        queryBuilder {
            setAttributesToRetrieve(attributeA, attributeB)
            attributesToRetrieve shouldEqual listOf(attributeA, attributeB)
            setAttributesToRetrieve(attributeA, attributeB, excludeAttributes = true)
            attributesToRetrieve shouldEqual listOf(
                Attribute("-attributeA"),
                Attribute("-attributeB"),
                all
            )
            setRetrieveAllAttributes()
        }
    }

    @Test
    fun restrictSearchableAttributes() {
        queryBuilder {
            setRestrictSearchableAttributes(attributeA, attributeB)
            restrictSearchableAttributes shouldEqual listOf(attributeA, attributeB)
        }
    }

    @Test
    fun facets() {
        queryBuilder {
            setFacets(attributeA, attributeB)
            facets shouldEqual listOf(attributeA, attributeB)
            setAllFacets()
            facets shouldEqual listOf(all)
        }
    }

    @Test
    fun attributesToHighlight() {
        queryBuilder {
            setAttributesToHighlight(attributeA, attributeB)
            attributesToHighlight shouldEqual listOf(attributeA, attributeB)
            setHighlightAllAttributes()
            attributesToHighlight shouldEqual listOf(all)
        }
    }

    @Test
    fun attributesToSnippet() {
        queryBuilder {
            setAttributesToSnippet(attributeA to 10, attributeB to null)
            attributesToSnippet?.map { it.raw } shouldEqual listOf("attributeA:10", "attributeB")
            setSnippetAllAttributes()
            attributesToSnippet?.map { it.raw } shouldEqual listOf("*")
            setSnippetAllAttributes(10)
            attributesToSnippet?.map { it.raw } shouldEqual listOf("*:10")
        }
    }

    @Test
    fun disableTypoToleranceOnAttributes() {
        queryBuilder {
            setDisableTypoToleranceOnAttributes(attributeA, attributeB)
            disableTypoToleranceOnAttributes shouldEqual listOf(attributeA, attributeB)
        }
    }

    @Test
    fun queryLanguages() {
        queryBuilder {
            setQueryLanguages(QueryLanguage.Afrikaans, QueryLanguage.Albanian)
            queryLanguages shouldEqual listOf(QueryLanguage.Afrikaans, QueryLanguage.Albanian)
        }
    }

    @Test
    fun ruleContexts() {
        queryBuilder {
            setRuleContexts("mobile", "desktop")
            ruleContexts shouldEqual listOf("mobile", "desktop")
        }
    }

    @Test
    fun optionalWords() {
        queryBuilder {
            setOptionalWords("mobile", "desktop")
            optionalWords shouldEqual listOf("mobile", "desktop")
        }
    }

    @Test
    fun disableExactOnAttributes() {
        queryBuilder {
            setDisableExactOnAttributes(attributeA, attributeB)
            disableExactOnAttributes shouldEqual listOf(attributeA, attributeB)
        }
    }

    @Test
    fun alternativesAsExact() {
        queryBuilder {
            setAlternativesAsExact(AlternativesAsExact.IgnorePlurals)
            alternativesAsExact shouldEqual listOf(AlternativesAsExact.IgnorePlurals)
        }
    }

    @Test
    fun analyticsTags() {
        queryBuilder {
            setAnalyticsTags("mobile", "desktop")
            analyticsTags shouldEqual listOf("mobile", "desktop")
        }
    }

    @Test
    fun responseFields() {
        queryBuilder {
            setResponseFields(ResponseFields.All)
            responseFields shouldEqual listOf(ResponseFields.All)
        }
    }

    @Test
    fun filterBuilder() {
        val query = queryBuilder {
            filterBuilder {
                groupOrA.addAll(listOf(facetA, facetB))
            }
        }
        query.filterBuilder.buildTest() shouldEqual "attributeA:facetA OR attributeB:false"
    }

    @Test
    fun optionalFilterBuilder() {
        val query = queryBuilder {
            optionalFilterBuilder {
                groupOrA.addAll(listOf(facetA, facetB))
            }
        }
        query.optionalFilterBuilder.build() shouldEqual listOf(listOf(facetA.expression, facetB.expression))
    }
}