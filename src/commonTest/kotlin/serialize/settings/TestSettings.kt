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
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer
import serialize.search.TestSnippet
import shouldEqual
import string
import unknown
import kotlin.test.Test


internal class TestSettings : TestSerializer<Settings>(Settings.serializer()) {

    override val items = listOf(
        Settings(
            // Attributes
            searchableAttributes = attributes.map { SearchableAttribute.Default(it) },
            attributesForFaceting = attributes.map { AttributeForFaceting.Default(it) },
            unretrievableAttributes = attributes,
            attributesToRetrieve = attributes,
            // RankingCriterion
            ranking = listOf(RankingCriterion.Geo),
            customRanking = listOf(CustomRankingCriterion.Asc(attributeA)),
            replicas = listOf(indexA),
            // Faceting
            maxValuesPerFacet = int,
            sortFacetsBy = SortFacetsBy.Count,
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
            ignorePlurals = IgnorePlurals.True,
            removeStopWords = RemoveStopWords.True,
            camelCaseAttributes = attributes,
            decompoundedAttributes = listOf(TestDecompoundedAttributes.item),
            keepDiacriticsOnCharacters = string,
            queryLanguages = listOf(Language.Afrikaans, Language.Albanian),
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
            distinct = Distinct(int),
            replaceSynonymsInHighlight = boolean,
            minProximity = int,
            responseFields = listOf(ResponseFields.NbHits),
            maxFacetHits = int,
            version = int,
            advancedSyntaxFeatures = listOf(AdvancedSyntaxFeatures.ExcludeWords, AdvancedSyntaxFeatures.ExactPhrase),
            userData = json { unknown to unknown },
            indexLanguages = listOf(Language.Japanese)
        ) to json {
            // Attributes
            KeySearchableAttributes to attributesJson
            KeyAttributesForFaceting to attributesJson
            KeyUnretrievableAttributes to attributesJson
            KeyAttributesToRetrieve to attributesJson
            // RankingCriterion
            KeyRanking to jsonArray { +RankingCriterion.Geo.raw }
            KeyCustomRanking to jsonArray { +CustomRankingCriterion.Asc(attributeA).raw }
            KeyReplicas to jsonArray { +indexA.raw }
            // Faceting
            KeyMaxValuesPerFacet to int
            KeySortFacetValuesBy to SortFacetsBy.Count.raw
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
            KeyMinWordSizeFor1Typo to int
            KeyMinWordSizeFor2Typos to int
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
                +Language.Afrikaans.raw
                +Language.Albanian.raw
            }
            // Query-rules
            KeyEnableRules to boolean
            // Query-strategy
            KeyQueryType to QueryType.PrefixLast.raw
            KeyRemoveWordsIfNoResults to RemoveWordIfNoResults.LastWords.raw
            KeyAdvancedSyntax to boolean
            KeyAdvancedSyntaxFeatures to jsonArray {
                +KeyExcludeWords
                +KeyExactPhrase
            }
            KeyOptionalWords to jsonArray { +string }
            KeyDisableExactOnAttributes to attributesJson
            KeyDisablePrefixOnAttributes to attributesJson
            KeyExactOnSingleWordQuery to ExactOnSingleWordQuery.Word.raw
            KeyAlternativesAsExact to jsonArray { +AlternativesAsExact.IgnorePlurals.raw }
            // Performance
            KeyNumericAttributesForFiltering to jsonArray { +TestNumericAttribute.json }
            KeyAllowCompressionOfIntegerArray to boolean
            // Advanced
            KeyAttributeForDistinct to attributeA.raw
            KeyDistinct to int
            KeyReplaceSynonymsInHighlight to boolean
            KeyMinProximity to int
            KeyResponseFields to jsonArray { +ResponseFields.NbHits.raw }
            KeyMaxFacetHits to int
            KeyVersion to int
            KeyUserData to json { unknown to unknown }
            KeyPrimary to JsonNull
            KeyIndexLanguages to jsonArray { +Language.Japanese.raw }
        }
    )

    @Test
    fun encodeNoNull() {
        Settings().toJsonNoDefaults().toString() shouldEqual "{}"
    }
}