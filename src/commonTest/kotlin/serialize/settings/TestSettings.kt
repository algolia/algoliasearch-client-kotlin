package serialize.settings

import attributeA
import attributeB
import attributes
import attributesJson
import boolean
import com.algolia.search.model.search.AlternativesAsExact
import com.algolia.search.model.search.ExactOnSingleWordQuery
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.QueryType
import com.algolia.search.model.search.RemoveStopWords
import com.algolia.search.model.search.RemoveWordIfNoResults
import com.algolia.search.model.search.ResponseFields
import com.algolia.search.model.search.SortFacetsBy
import com.algolia.search.model.search.TypoTolerance
import com.algolia.search.model.settings.AdvancedSyntaxFeatures
import com.algolia.search.model.settings.AttributeForFaceting
import com.algolia.search.model.settings.CustomRankingCriterion
import com.algolia.search.model.settings.DecompoundedAttributes
import com.algolia.search.model.settings.Distinct
import com.algolia.search.model.settings.RankingCriterion
import com.algolia.search.model.settings.SearchableAttribute
import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.KeyAdvancedSyntax
import com.algolia.search.serialize.KeyAdvancedSyntaxFeatures
import com.algolia.search.serialize.KeyAllowCompressionOfIntegerArray
import com.algolia.search.serialize.KeyAllowTyposOnNumericTokens
import com.algolia.search.serialize.KeyAlternativesAsExact
import com.algolia.search.serialize.KeyAttributeCriteriaComputedByMinProximity
import com.algolia.search.serialize.KeyAttributeForDistinct
import com.algolia.search.serialize.KeyAttributesForFaceting
import com.algolia.search.serialize.KeyAttributesToHighlight
import com.algolia.search.serialize.KeyAttributesToRetrieve
import com.algolia.search.serialize.KeyAttributesToSnippet
import com.algolia.search.serialize.KeyAttributesToTransliterate
import com.algolia.search.serialize.KeyCamelCaseAttributes
import com.algolia.search.serialize.KeyCustomNormalization
import com.algolia.search.serialize.KeyCustomRanking
import com.algolia.search.serialize.KeyDecompoundQuery
import com.algolia.search.serialize.KeyDecompoundedAttributes
import com.algolia.search.serialize.KeyDisableExactOnAttributes
import com.algolia.search.serialize.KeyDisablePrefixOnAttributes
import com.algolia.search.serialize.KeyDisableTypoToleranceOnAttributes
import com.algolia.search.serialize.KeyDisableTypoToleranceOnWords
import com.algolia.search.serialize.KeyDistinct
import com.algolia.search.serialize.KeyEnablePersonalization
import com.algolia.search.serialize.KeyEnableRules
import com.algolia.search.serialize.KeyExactOnSingleWordQuery
import com.algolia.search.serialize.KeyExactPhrase
import com.algolia.search.serialize.KeyExcludeWords
import com.algolia.search.serialize.KeyHighlightPostTag
import com.algolia.search.serialize.KeyHighlightPreTag
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyIgnorePlurals
import com.algolia.search.serialize.KeyIndexLanguages
import com.algolia.search.serialize.KeyKeepDiacriticsOnCharacters
import com.algolia.search.serialize.KeyMaxFacetHits
import com.algolia.search.serialize.KeyMaxValuesPerFacet
import com.algolia.search.serialize.KeyMinProximity
import com.algolia.search.serialize.KeyMinWordSizeFor1Typo
import com.algolia.search.serialize.KeyMinWordSizeFor2Typos
import com.algolia.search.serialize.KeyNumericAttributesForFiltering
import com.algolia.search.serialize.KeyOptionalWords
import com.algolia.search.serialize.KeyPaginationLimitedTo
import com.algolia.search.serialize.KeyPrimary
import com.algolia.search.serialize.KeyQueryLanguages
import com.algolia.search.serialize.KeyQueryType
import com.algolia.search.serialize.KeyRanking
import com.algolia.search.serialize.KeyRelevancyStrictness
import com.algolia.search.serialize.KeyRemoveStopWords
import com.algolia.search.serialize.KeyRemoveWordsIfNoResults
import com.algolia.search.serialize.KeyReplaceSynonymsInHighlight
import com.algolia.search.serialize.KeyReplicas
import com.algolia.search.serialize.KeyResponseFields
import com.algolia.search.serialize.KeyRestrictHighlightAndSnippetArrays
import com.algolia.search.serialize.KeySearchableAttributes
import com.algolia.search.serialize.KeySeparatorsToIndex
import com.algolia.search.serialize.KeySnippetEllipsisText
import com.algolia.search.serialize.KeySortFacetValuesBy
import com.algolia.search.serialize.KeyTypoTolerance
import com.algolia.search.serialize.KeyUnretrievableAttributes
import com.algolia.search.serialize.KeyUserData
import com.algolia.search.serialize.KeyVersion
import com.algolia.search.serialize.internal.toJsonNoDefaults
import indexA
import int
import kotlin.test.Test
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import serialize.search.TestSnippet
import shouldEqual
import string
import unknown

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
            decompoundedAttributes = listOf(
                DecompoundedAttributes(
                    Language.German,
                    attributeA,
                    attributeB
                )
            ),
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
            userData = buildJsonObject { put(unknown, unknown) },
            indexLanguages = listOf(Language.Japanese),
            customNormalization = mapOf(unknown to mapOf(unknown to unknown)),
            enablePersonalization = boolean,
            attributeCriteriaComputedByMinProximity = boolean,
            relevancyStrictness = int,
            decompoundQuery = boolean,
            attributesToTransliterate = attributes
        ) to buildJsonObject {
            // Attributes
            put(KeySearchableAttributes, attributesJson)
            put(KeyAttributesForFaceting, attributesJson)
            put(KeyUnretrievableAttributes, attributesJson)
            put(KeyAttributesToRetrieve, attributesJson)
            // RankingCriterion
            put(KeyRanking, buildJsonArray { add(RankingCriterion.Geo.raw) })
            put(KeyCustomRanking, buildJsonArray { add(CustomRankingCriterion.Asc(attributeA).raw) })
            put(KeyReplicas, buildJsonArray { add(indexA.raw) })
            // Faceting
            put(KeyMaxValuesPerFacet, int)
            put(KeySortFacetValuesBy, SortFacetsBy.Count.raw)
            // Highlighting
            put(KeyAttributesToHighlight, attributesJson)
            put(KeyAttributesToSnippet, buildJsonArray { add(TestSnippet.json) })
            put(KeyHighlightPreTag, string)
            put(KeyHighlightPostTag, string)
            put(KeySnippetEllipsisText, string)
            put(KeyRestrictHighlightAndSnippetArrays, boolean)
            // Pagination
            put(KeyHitsPerPage, int)
            put(KeyPaginationLimitedTo, int)
            put(KeyMinWordSizeFor1Typo, int)
            put(KeyMinWordSizeFor2Typos, int)
            // Typos
            put(KeyTypoTolerance, TypoTolerance.Min.raw)
            put(KeyAllowTyposOnNumericTokens, boolean)
            put(KeyDisableTypoToleranceOnAttributes, attributesJson)
            put(KeyDisableTypoToleranceOnWords, buildJsonArray { add(string) })
            put(KeySeparatorsToIndex, string)
            // Languages
            put(KeyIgnorePlurals, boolean)
            put(KeyRemoveStopWords, boolean)
            put(KeyCamelCaseAttributes, attributesJson)
            put(
                KeyDecompoundedAttributes,
                buildJsonObject {
                    put(
                        Language.German.raw,
                        buildJsonArray {
                            add(attributeA.raw)
                            add(attributeB.raw)
                        }
                    )
                }
            )
            put(KeyKeepDiacriticsOnCharacters, string)
            put(
                KeyQueryLanguages,
                buildJsonArray {
                    add(Language.Afrikaans.raw)
                    add(Language.Albanian.raw)
                }
            )
            // Query-rules
            put(KeyEnableRules, boolean)
            // Query-strategy
            put(KeyQueryType, QueryType.PrefixLast.raw)
            put(KeyRemoveWordsIfNoResults, RemoveWordIfNoResults.LastWords.raw)
            put(KeyAdvancedSyntax, boolean)
            put(
                KeyAdvancedSyntaxFeatures,
                buildJsonArray {
                    add(KeyExcludeWords)
                    add(KeyExactPhrase)
                }
            )
            put(KeyOptionalWords, buildJsonArray { add(string) })
            put(KeyDisableExactOnAttributes, attributesJson)
            put(KeyDisablePrefixOnAttributes, attributesJson)
            put(KeyExactOnSingleWordQuery, ExactOnSingleWordQuery.Word.raw)
            put(KeyAlternativesAsExact, buildJsonArray { add(AlternativesAsExact.IgnorePlurals.raw) })
            // Performance
            put(KeyNumericAttributesForFiltering, buildJsonArray { add(TestNumericAttribute.json) })
            put(KeyAllowCompressionOfIntegerArray, boolean)
            // Advanced
            put(KeyAttributeForDistinct, attributeA.raw)
            put(KeyDistinct, int)
            put(KeyReplaceSynonymsInHighlight, boolean)
            put(KeyMinProximity, int)
            put(KeyResponseFields, buildJsonArray { add(ResponseFields.NbHits.raw) })
            put(KeyMaxFacetHits, int)
            put(KeyVersion, int)
            put(KeyUserData, buildJsonObject { put(unknown, unknown) })
            put(KeyPrimary, JsonNull)
            put(KeyIndexLanguages, buildJsonArray { add(Language.Japanese.raw) })
            put(KeyCustomNormalization, buildJsonObject { put(unknown, buildJsonObject { put(unknown, unknown) }) })
            put(KeyEnablePersonalization, boolean)
            put(KeyAttributeCriteriaComputedByMinProximity, boolean)
            put(KeyRelevancyStrictness, int)
            put(KeyDecompoundQuery, boolean)
            put(KeyAttributesToTransliterate, attributesJson)
        }
    )

    @Test
    fun encodeNoNull() {
        Settings().toJsonNoDefaults().toString() shouldEqual "{}"
    }
}
