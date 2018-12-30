package query

import buildTest
import client.data.AlternativesAsExact
import client.data.Attribute
import client.data.QueryLanguage
import client.data.ResponseFields
import client.query.helper.*
import client.to
import facetA
import facetB
import groupOrA
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
class TestQueryHelper {

    private val attributeA = Attribute("attributeA")
    private val attributeB = Attribute("attributeB")

    @Test
    fun attributesToRetrieve() {
        queryBuilder {
            setAttributesToRetrieve(attributeA, attributeB)
            listOf(attributeA, attributeB) shouldEqual attributesToRetrieve
            setAttributesToRetrieve(attributeA, attributeB, excludeAttributes = true)
            listOf(Attribute("-attributeA"), Attribute("-attributeB"), all) shouldEqual attributesToRetrieve
            setRetrieveAllAttributes()
        }
    }

    @Test
    fun restrictSearchableAttributes() {
        queryBuilder {
            setRestrictSearchableAttributes(attributeA, attributeB)
            listOf(attributeA, attributeB) shouldEqual restrictSearchableAttributes
        }
    }

    @Test
    fun facets() {
        queryBuilder {
            setFacets(attributeA, attributeB)
            listOf(attributeA, attributeB) shouldEqual facets
            setAllFacets()
            listOf(all) shouldEqual facets
        }
    }

    @Test
    fun attributesToHighlight() {
        queryBuilder {
            setAttributesToHighlight(attributeA, attributeB)
            listOf(attributeA, attributeB) shouldEqual attributesToHighlight
            setHighlightAllAttributes()
            listOf(all) shouldEqual attributesToHighlight
        }
    }

    @Test
    fun attributesToSnippet() {
        queryBuilder {
            setAttributesToSnippet(attributeA to 10, attributeB to null)
            listOf("attributeA:10", "attributeB") shouldEqual attributesToSnippet?.map { it.raw }
            setSnippetAllAttributes()
            listOf("*") shouldEqual attributesToSnippet?.map { it.raw }
            setSnippetAllAttributes(10)
            listOf("*:10") shouldEqual attributesToSnippet?.map { it.raw }
        }
    }

    @Test
    fun disableTypoToleranceOnAttributes() {
        queryBuilder {
            setDisableTypoToleranceOnAttributes(attributeA, attributeB)
            listOf(attributeA, attributeB) shouldEqual disableTypoToleranceOnAttributes
        }
    }

    @Test
    fun queryLanguages() {
        queryBuilder {
            setQueryLanguages(QueryLanguage.Afrikaans, QueryLanguage.Albanian)
            listOf(QueryLanguage.Afrikaans, QueryLanguage.Albanian) shouldEqual queryLanguages
        }
    }

    @Test
    fun ruleContexts() {
        queryBuilder {
            setRuleContexts("mobile", "desktop")
            listOf("mobile", "desktop") shouldEqual ruleContexts
        }
    }

    @Test
    fun optionalWords() {
        queryBuilder {
            setOptionalWords("mobile", "desktop")
            listOf("mobile", "desktop") shouldEqual optionalWords
        }
    }

    @Test
    fun disableExactOnAttributes() {
        queryBuilder {
            setDisableExactOnAttributes(attributeA, attributeB)
            listOf(attributeA, attributeB) shouldEqual disableExactOnAttributes
        }
    }

    @Test
    fun alternativesAsExact() {
        queryBuilder {
            setAlternativesAsExact(AlternativesAsExact.IgnorePlurals)
            listOf(AlternativesAsExact.IgnorePlurals) shouldEqual alternativesAsExact
        }
    }

    @Test
    fun analyticsTags() {
        queryBuilder {
            setAnalyticsTags("mobile", "desktop")
            listOf("mobile", "desktop") shouldEqual analyticsTags
        }
    }

    @Test
    fun responseFields() {
        queryBuilder {
            setResponseFields(ResponseFields.All)
            listOf(ResponseFields.All) shouldEqual responseFields
        }
    }

    @Test
    fun filterBuilder() {
        val query = queryBuilder {
            filterBuilder {
                groupOrA.addAll(listOf(facetA, facetB))
            }
        }
        "attributeA:facetA OR attributeB:false" shouldEqual query.filterBuilder.buildTest()
    }

    @Test
    fun optionalFilterBuilder() {
        val query = queryBuilder {
            optionalFilterBuilder {
                groupOrA.addAll(listOf(facetA, facetB))
            }
        }
        listOf(listOf(facetA.expression, facetB.expression)) shouldEqual query.optionalFilterBuilder.build()
    }
}