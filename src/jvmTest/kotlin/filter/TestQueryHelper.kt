package filter

import com.algolia.search.filter.*
import com.algolia.search.helper.limit
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.AlternativesAsExact
import com.algolia.search.model.search.QueryLanguage
import com.algolia.search.model.search.ResponseFields
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestQueryHelper {

    private val attributeA = Attribute("attributeA")
    private val attributeB = Attribute("attributeB")

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
            setAllAttributesToRetrieve()
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
            setAllAttributesToHighlight()
            attributesToHighlight shouldEqual listOf(all)
        }
    }

    @Test
    fun attributesToSnippet() {
        queryBuilder {
            setAttributesToSnippet(attributeA limit 10, attributeB limit null)
            attributesToSnippet?.map { it.raw } shouldEqual listOf("attributeA:10", "attributeB")
            setAllAttributesToSnippet()
            attributesToSnippet?.map { it.raw } shouldEqual listOf("*")
            setAllAttributesToSnippet(10)
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
}