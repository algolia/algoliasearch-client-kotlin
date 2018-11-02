import client.query.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class TestSerialization {

    @Test
    fun aroundRadius() {
        val inMeters = Query(aroundRadius = AroundRadius.InMeters(10))
        val all = Query(aroundRadius = AroundRadius.All)

        assertEquals("{\"aroundRadius\":10}", inMeters.stringify())
        assertEquals("{\"aroundRadius\":\"all\"}", all.stringify())
    }

    @Test
    fun booleanOrQueryLanguages() {
        val boolean = Query(ignorePlurals = BooleanOrQueryLanguage.Boolean(true))
        val languages = Query(
            ignorePlurals = BooleanOrQueryLanguage.QueryLanguages(
                QueryLanguage.Afrikaans,
                QueryLanguage.Albanian
            )
        )

        assertEquals("{\"ignorePlurals\":true}", boolean.stringify())
        assertEquals("{\"ignorePlurals\":[\"af\",\"sq\"]}", languages.stringify())
    }

    @Test
    fun typoTolerance() {
        val min = Query(typoTolerance = TypoTolerance.Min)
        val strict = Query(typoTolerance = TypoTolerance.Strict)
        val boolean = Query(typoTolerance = TypoTolerance.Boolean(true))

        assertEquals("{\"typoTolerance\":\"min\"}", min.stringify())
        assertEquals("{\"typoTolerance\":\"strict\"}", strict.stringify())
        assertEquals("{\"typoTolerance\":true}", boolean.stringify())
    }

    @Test
    fun default() {
        val query = Query()

        assertEquals("{}", query.stringify())
    }

    // Search
    @Test
    fun query() {
        val query = Query(query = "")

        assertEquals("{\"query\":\"\"}", query.stringify())
    }

    // Attributes
    @Test
    fun attributesToRetrieve() {
        val query = Query(attributesToRetrieve = listOf())

        assertEquals("{\"attributesToRetrieve\":[]}", query.stringify())
    }

    @Test
    fun restrictSearchableAttributes() {
        val query = Query(restrictSearchableAttributes = listOf())

        assertEquals("{\"restrictSearchableAttributes\":[]}", query.stringify())
    }

    // Filters
    @Test
    fun filters() {
        val query = Query(filters = "")

        assertEquals("{\"filters\":\"\"}", query.stringify())
    }

    @Test
    fun facetFilters() {
        val query = Query(facetFilters = listOf())

        assertEquals("{\"facetFilters\":[]}", query.stringify())
    }

    @Test
    fun optionalFilters() {
        val query = Query(optionalFilters = listOf())

        assertEquals("{\"optionalFilters\":[]}", query.stringify())
    }

    @Test
    fun numericFilters() {
        val query = Query(numericFilters = listOf())

        assertEquals("{\"numericFilters\":[]}", query.stringify())
    }

    @Test
    fun tagFilters() {
        val query = Query(tagFilters = listOf())

        assertEquals("{\"tagFilters\":[]}", query.stringify())
    }

    @Test
    fun sumOrFiltersScores() {
        val query = Query(sumOrFiltersScores = true)

        assertEquals("{\"sumOrFiltersScores\":true}", query.stringify())
    }

    // Faceting
    @Test
    fun facets() {
        val query = Query(facets = listOf())

        assertEquals("{\"facets\":[]}", query.stringify())
    }

    @Test
    fun maxValuesPerFacet() {
        val query = Query(maxValuesPerFacet = 10)

        assertEquals("{\"maxValuesPerFacet\":10}", query.stringify())
    }

    @Test
    fun facetingAfterDistinct() {
        val query = Query(facetingAfterDistinct = true)

        assertEquals("{\"facetingAfterDistinct\":true}", query.stringify())
    }

    @Test
    fun sortFacetValuesBy() {
        val query = Query(sortFacetValuesBy = SortFacetValuesBy.Count)

        assertEquals("{\"sortFacetValuesBy\":\"count\"}", query.stringify())
    }

    // Highlighting
    @Test
    fun attributesToHighlight() {
        val query = Query(attributesToHighlight = listOf())

        assertEquals("{\"attributesToHighlight\":[]}", query.stringify())
    }

    @Test
    fun attributesToSnippet() {
        val query = Query(attributesToSnippet = listOf())

        assertEquals("{\"attributesToSnippet\":[]}", query.stringify())
    }

    @Test
    fun highlightPreTag() {
        val query = Query(highlightPreTag = "")

        assertEquals("{\"highlightPreTag\":\"\"}", query.stringify())
    }

    @Test
    fun highlightPostTag() {
        val query = Query(highlightPostTag = "")

        assertEquals("{\"highlightPostTag\":\"\"}", query.stringify())
    }

    @Test
    fun snippetEllipsisText() {
        val query = Query(snippetEllipsisText = "")

        assertEquals("{\"snippetEllipsisText\":\"\"}", query.stringify())
    }

    @Test
    fun restrictHighlightAndSnippetArrays() {
        val query = Query(restrictHighlightAndSnippetArrays = true)

        assertEquals("{\"restrictHighlightAndSnippetArrays\":true}", query.stringify())
    }

    // Pagination
    @Test
    fun page() {
        val query = Query(page = 10)

        assertEquals("{\"page\":10}", query.stringify())
    }

    @Test
    fun hitsPerPage() {
        val query = Query(hitsPerPage = 10)

        assertEquals("{\"hitsPerPage\":10}", query.stringify())
    }

    @Test
    fun offset() {
        val query = Query(offset = 10)

        assertEquals("{\"offset\":10}", query.stringify())
    }

    @Test
    fun length() {
        val query = Query(length = 10)

        assertEquals("{\"length\":10}", query.stringify())
    }

    // Typos
    @Test
    fun minWordSizefor1Typo() {
        val query = Query(minWordSizefor1Typo = 10)

        assertEquals("{\"minWordSizefor1Typo\":10}", query.stringify())
    }

    @Test
    fun minWordSizefor2Typos() {
        val query = Query(minWordSizefor2Typos = 10)

        assertEquals("{\"minWordSizefor2Typos\":10}", query.stringify())
    }

    @Test
    fun allowTyposOnNumericTokens() {
        val query = Query(allowTyposOnNumericTokens = true)

        assertEquals("{\"allowTyposOnNumericTokens\":true}", query.stringify())
    }

    @Test
    fun disableTypoToleranceOnAttributes() {
        val query = Query(disableTypoToleranceOnAttributes = listOf())

        assertEquals("{\"disableTypoToleranceOnAttributes\":[]}", query.stringify())
    }

    // Geo-search
    @Test
    fun aroundLatLng() {
        val query = Query(aroundLatLng = "")

        assertEquals("{\"aroundLatLng\":\"\"}", query.stringify())
    }

    @Test
    fun aroundLatLngViaIP() {
        val query = Query(aroundLatLngViaIP = true)

        assertEquals("{\"aroundLatLngViaIP\":true}", query.stringify())
    }

    @Test
    fun aroundPrecision() {
        val query = Query(aroundPrecision = 10)

        assertEquals("{\"aroundPrecision\":10}", query.stringify())
    }

    @Test
    fun minimumAroundRadius() {
        val query = Query(minimumAroundRadius = 10)

        assertEquals("{\"minimumAroundRadius\":10}", query.stringify())
    }

    @Test
    fun insideBoundingBox() {
        val query = Query(insideBoundingBox = listOf())

        assertEquals("{\"insideBoundingBox\":[]}", query.stringify())
    }

    @Test
    fun insidePolygon() {
        val query = Query(insidePolygon = listOf())

        assertEquals("{\"insidePolygon\":[]}", query.stringify())
    }

    // Query-rules
    @Test
    fun enableRules() {
        val query = Query(enableRules = true)

        assertEquals("{\"enableRules\":true}", query.stringify())
    }

    @Test
    fun ruleContexts() {
        val query = Query(ruleContexts = listOf())

        assertEquals("{\"ruleContexts\":[]}", query.stringify())
    }

    // Query-strategy

    @Test
    fun queryType() {
        val query = Query(queryType = QueryType.PrefixNone)

        assertEquals("{\"queryType\":\"prefixNone\"}", query.stringify())
    }

    @Test
    fun removeWordsIfNoResults() {
        val query = Query(removeWordsIfNoResults = RemoveWordIfNoResults.None)

        assertEquals("{\"removeWordsIfNoResults\":\"none\"}", query.stringify())
    }

    @Test
    fun advancedSyntax() {
        val query = Query(advancedSyntax = true)

        assertEquals("{\"advancedSyntax\":true}", query.stringify())
    }

    @Test
    fun optionalWords() {
        val query = Query(optionalWords = listOf())

        assertEquals("{\"optionalWords\":[]}", query.stringify())
    }

    @Test
    fun disableExactOnAttributes() {
        val query = Query(disableExactOnAttributes = listOf())

        assertEquals("{\"disableExactOnAttributes\":[]}", query.stringify())
    }

    @Test
    fun exactOnSingleWordQuery() {
        val query = Query(exactOnSingleWordQuery = ExactOnSingleWordQuery.None)

        assertEquals("{\"exactOnSingleWordQuery\":\"none\"}", query.stringify())
    }

    @Test
    fun alternativesAsExact() {
        val query = Query(alternativesAsExact = listOf())

        assertEquals("{\"alternativesAsExact\":[]}", query.stringify())
    }

    // Advanced
    @Test
    fun distinct() {
        val query = Query(distinct = 10)

        assertEquals("{\"distinct\":10}", query.stringify())
    }

    @Test
    fun getRankingInfo() {
        val query = Query(getRankingInfo = true)

        assertEquals("{\"getRankingInfo\":true}", query.stringify())
    }

    @Test
    fun clickAnalytics() {
        val query = Query(clickAnalytics = true)

        assertEquals("{\"clickAnalytics\":true}", query.stringify())
    }

    @Test
    fun analytics() {
        val query = Query(analytics = true)

        assertEquals("{\"analytics\":true}", query.stringify())
    }

    @Test
    fun analyticsTags() {
        val query = Query(analyticsTags = listOf())

        assertEquals("{\"analyticsTags\":[]}", query.stringify())
    }

    @Test
    fun synonyms() {
        val query = Query(synonyms = true)

        assertEquals("{\"synonyms\":true}", query.stringify())
    }

    @Test
    fun replaceSynonymsInHighlight() {
        val query = Query(replaceSynonymsInHighlight = true)

        assertEquals("{\"replaceSynonymsInHighlight\":true}", query.stringify())
    }

    @Test
    fun minProximity() {
        val query = Query(minProximity = 10)

        assertEquals("{\"minProximity\":10}", query.stringify())
    }

    @Test
    fun responseFields() {
        val query = Query(responseFields = listOf())

        assertEquals("{\"responseFields\":[]}", query.stringify())
    }

    @Test
    fun maxFacetHits() {
        val query = Query(maxFacetHits = 10)

        assertEquals("{\"maxFacetHits\":10}", query.stringify())
    }

    @Test
    fun percentileComputation() {
        val query = Query(percentileComputation = true)

        assertEquals("{\"percentileComputation\":true}", query.stringify())
    }
}