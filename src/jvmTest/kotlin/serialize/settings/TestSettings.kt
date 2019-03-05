package serialize.settings

import attributeA
import attributes
import attributesJson
import boolean
import com.algolia.search.model.search.*
import com.algolia.search.model.settings.*
import com.algolia.search.serialize.*
import indexA
import int
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import serialize.search.TestSnippet
import shouldEqual
import string


@RunWith(JUnit4::class)
internal class TestSettings : TestSerializer<Settings>(Settings.serializer()) {

    override val items = listOf(
        Settings(
            // Attributes
            searchableAttributes = attributes.map { SearchableAttribute.Default(it) },
            attributesForFaceting = attributes.map { AttributeForFaceting.Default(it) },
            unretrievableAttributes = attributes,
            attributesToRetrieve = attributes,
            // Ranking
            ranking = listOf(Ranking.Geo),
            customRanking = listOf(CustomRanking.Asc(attributeA)),
            replicas = listOf(indexA),
            // Faceting
            maxValuesPerFacet = int,
            sortFacetValuesBy = SortFacetValuesBy.Count,
            // Highlighting
            attributesToHighlight = attributes,
            attributesToSnippet = listOf(TestSnippet.snippet),
            highlightPreTag = string,
            highlightPostTag = string,
            snippetEllipsisText = string,
            restrictHighlightAndSnippetArrays = boolean,
            // Pagination
            hitsPerPage = int,
            paginationLimitedTo = int,
            minWordSizeFor1Typo = int,
            minWordSizeFor2Typos = int,
            // Typos
            typoTolerance = TypoTolerance.Min,
            allowTyposOnNumericTokens = boolean,
            disableTypoToleranceOnAttributes = attributes,
            disableTypoToleranceOnWords = listOf(string),
            separatorsToIndex = string,
            // Languages
            ignorePlurals = BooleanOrQueryLanguages.Boolean(boolean),
            removeStopWords = BooleanOrQueryLanguages.Boolean(boolean),
            camelCaseAttributes = attributes,
            decompoundedAttributes = listOf(TestDecompoundedAttributes.item),
            keepDiacriticsOnCharacters = string,
            queryLanguages = listOf(QueryLanguage.Afrikaans, QueryLanguage.Albanian),
            // Query-rules
            enableRules = boolean,
            // Query-strategy
            queryType = QueryType.PrefixLast,
            removeWordsIfNoResults = RemoveWordIfNoResults.LastWords,
            advancedSyntax = boolean,
            optionalWords = listOf(string),
            disablePrefixOnAttributes = attributes,
            disableExactOnAttributes = attributes,
            exactOnSingleWordQuery = ExactOnSingleWordQuery.Word,
            alternativesAsExact = listOf(AlternativesAsExact.IgnorePlurals),
            // Performance
            numericAttributesForFiltering = listOf(TestNumericAttribute.item),
            allowCompressionOfIntegerArray = boolean,
            // Advanced
            attributeForDistinct = attributeA,
            distinct = Distinct.True,
            replaceSynonymsInHighlight = boolean,
            minProximity = int,
            responseFields = listOf(ResponseFields.NbHits),
            maxFacetHits = int,
            version = int
        ) to json {
            // Attributes
            KeySearchableAttributes to attributesJson
            KeyAttributesForFaceting to attributesJson
            KeyUnretrievableAttributes to attributesJson
            KeyAttributesToRetrieve to attributesJson
            // Ranking
            KeyRanking to jsonArray { +Ranking.Geo.raw }
            KeyCustomRanking to jsonArray { +CustomRanking.Asc(attributeA).raw }
            KeyReplicas to jsonArray { +indexA.raw }
            // Faceting
            KeyMaxValuesPerFacet to int
            KeySortFacetValuesBy to SortFacetValuesBy.Count.raw
            // Highlighting
            KeyAttributesToHighlight to attributesJson
            KeyAttributesToSnippet to jsonArray { +TestSnippet.json }
            KeyHighlightPreTag to string
            KeyHighlightPostTag to string
            KeySnippetEllipsisText to string
            KeyRestrictHighlightAndSnippetArrays to boolean
            // Pagination
            KeyHitsPerPage to int
            KeyPaginationLimitedTo to int
            KeyMinWordSizefor1Typo to int
            KeyMinWordSizefor2Typos to int
            // Typos
            KeyTypoTolerance to TypoTolerance.Min.raw
            KeyAllowTyposOnNumericTokens to boolean
            KeyDisableTypoToleranceOnAttributes to attributesJson
            KeyDisableTypoToleranceOnWords to jsonArray { +string }
            KeySeparatorsToIndex to string
            // Languages
            KeyIgnorePlurals to boolean
            KeyRemoveStopWords to boolean
            KeyCamelCaseAttributes to attributesJson
            KeyDecompoundedAttributes to jsonArray { +TestDecompoundedAttributes.json }
            KeyKeepDiacriticsOnCharacters to string
            KeyQueryLanguages to jsonArray {
                +QueryLanguage.Afrikaans.raw
                +QueryLanguage.Albanian.raw
            }
            // Query-rules
            KeyEnableRules to boolean
            // Query-strategy
            KeyQueryType to QueryType.PrefixLast.raw
            KeyRemoveWordsIfNoResults to RemoveWordIfNoResults.LastWords.raw
            KeyAdvancedSyntax to boolean
            KeyOptionalWords to jsonArray { +string }
            KeyDisablePrefixOnAttributes to attributesJson
            KeyDisableExactOnAttributes to attributesJson
            KeyExactOnSingleWordQuery to ExactOnSingleWordQuery.Word.raw
            KeyAlternativesAsExact to jsonArray { +AlternativesAsExact.IgnorePlurals.raw }
            // Performance
            KeyNumericAttributesForFiltering to jsonArray { +TestNumericAttribute.json }
            KeyAllowCompressionOfIntegerArray to boolean
            // Advanced
            KeyAttributeForDistinct to attributeA.raw
            KeyDistinct to true
            KeyReplaceSynonymsInHighlight to boolean
            KeyMinProximity to int
            KeyResponseFields to jsonArray { +ResponseFields.NbHits.raw }
            KeyMaxFacetHits to int
            KeyVersion to int
        }
    )

    @Test
    fun encodeNoNull() {
        Settings().toJsonNoDefaults().toString() shouldEqual "{}"
    }
}