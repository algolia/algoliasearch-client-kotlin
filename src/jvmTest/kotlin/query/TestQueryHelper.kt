package query

import client.query.AlternativesAsExact
import client.query.QueryLanguage
import client.query.ResponseFields
import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestQueryHelper {

    private val attributeA = Attribute("attributeA")
    private val attributeB = Attribute("attributeB")

    @Test
    fun attributesToRetrieve() {
        queryBuilder {
            setAttributesToRetrieve(attributeA, attributeB)
            assertEquals(listOf(attributeA, attributeB), attributesToRetrieve)
            setAttributesToRetrieve(attributeA, attributeB, excludeAttributes = true)
            assertEquals(listOf(Attribute("-attributeA"), Attribute("-attributeB"), all), attributesToRetrieve)
            setRetrieveAllAttributes()
        }
    }

    @Test
    fun restrictSearchableAttributes() {
        queryBuilder {
            setRestrictSearchableAttributes(attributeA, attributeB)
            assertEquals(listOf(attributeA, attributeB), restrictSearchableAttributes)
        }
    }

    @Test
    fun facets() {
        queryBuilder {
            setFacets(attributeA, attributeB)
            assertEquals(listOf(attributeA, attributeB), facets)
            setAllFacets()
            assertEquals(listOf(all), facets)
        }
    }

    @Test
    fun attributesToHighlight() {
        queryBuilder {
            setAttributesToHighlight(attributeA, attributeB)
            assertEquals(listOf(attributeA, attributeB), attributesToHighlight)
            setHighlightAllAttributes()
            assertEquals(listOf(all), attributesToHighlight)
        }
    }

    @Test
    fun attributesToSnippet() {
        queryBuilder {
            setAttributesToSnippet(attributeA to 10, attributeB to null)
            assertEquals(listOf("attributeA:10", "attributeB"), attributesToSnippet)
            setSnippetAllAttributes()
            assertEquals(listOf("*"), attributesToSnippet)
            setSnippetAllAttributes(10)
            assertEquals(listOf("*:10"), attributesToSnippet)
        }
    }

    @Test
    fun disableTypoToleranceOnAttributes() {
        queryBuilder {
            setDisableTypoToleranceOnAttributes(attributeA, attributeB)
            assertEquals(listOf(attributeA, attributeB), disableTypoToleranceOnAttributes)
        }
    }

    @Test
    fun queryLanguages() {
        queryBuilder {
            setQueryLanguages(QueryLanguage.Afrikaans, QueryLanguage.Albanian)
            assertEquals(listOf(QueryLanguage.Afrikaans, QueryLanguage.Albanian), queryLanguages)
        }
    }

    @Test
    fun ruleContexts() {
        queryBuilder {
            setRuleContexts("mobile", "desktop")
            assertEquals(listOf("mobile", "desktop"), ruleContexts)
        }
    }

    @Test
    fun optionalWords() {
        queryBuilder {
            setOptionalWords("mobile", "desktop")
            assertEquals(listOf("mobile", "desktop"), optionalWords)
        }
    }

    @Test
    fun disableExactOnAttributes() {
        queryBuilder {
            setDisableExactOnAttributes(attributeA, attributeB)
            assertEquals(listOf(attributeA, attributeB), disableExactOnAttributes)
        }
    }

    @Test
    fun alternativesAsExact() {
        queryBuilder {
            setAlternativesAsExact(AlternativesAsExact.IgnorePlurals)
            assertEquals(listOf(AlternativesAsExact.IgnorePlurals), alternativesAsExact)
        }
    }

    @Test
    fun analyticsTags() {
        queryBuilder {
            setAnalyticsTags("mobile", "desktop")
            assertEquals(listOf("mobile", "desktop"), analyticsTags)
        }
    }

    @Test
    fun responseFields() {
        queryBuilder {
            setResponseFields(ResponseFields.All)
            assertEquals(listOf(ResponseFields.All), responseFields)
        }
    }

    @Test
    fun filterBuilder() {
        val query = queryBuilder {
            filterBuilder {
                or(FilterFacet(attributeA, "valueA"), FilterFacet(attributeB, true))
            }
        }
        assertEquals("attributeA:valueA AND attributeB:true", query.filterBuilder.build())
    }

    @Test
    fun optionalFilterBuilder() {
        val query = queryBuilder {
            optionalFilterBuilder {
                and(FilterFacet(attributeA, "valueA"), FilterFacet(attributeA, true))
            }
        }
        assertEquals(
            listOf(listOf("attributeA:valueA"), listOf("attributeA:true")),
            query.optionalFilterBuilder.build()
        )
    }
}