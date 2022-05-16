package serialize.settings

import attributeA
import attributeB
import attributes
import attributesJson
import boolean
import com.algolia.search.model.rule.FacetOrdering
import com.algolia.search.model.rule.FacetValuesOrder
import com.algolia.search.model.rule.FacetsOrder
import com.algolia.search.model.rule.RenderingContent
import com.algolia.search.model.rule.SortRule
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
import com.algolia.search.serialize.internal.KeyAdvancedSyntax
import com.algolia.search.serialize.internal.KeyAdvancedSyntaxFeatures
import com.algolia.search.serialize.internal.KeyAllowCompressionOfIntegerArray
import com.algolia.search.serialize.internal.KeyAllowTyposOnNumericTokens
import com.algolia.search.serialize.internal.KeyAlpha
import com.algolia.search.serialize.internal.KeyAlternativesAsExact
import com.algolia.search.serialize.internal.KeyAttributeCriteriaComputedByMinProximity
import com.algolia.search.serialize.internal.KeyAttributeForDistinct
import com.algolia.search.serialize.internal.KeyAttributesForFaceting
import com.algolia.search.serialize.internal.KeyAttributesToHighlight
import com.algolia.search.serialize.internal.KeyAttributesToRetrieve
import com.algolia.search.serialize.internal.KeyAttributesToSnippet
import com.algolia.search.serialize.internal.KeyAttributesToTransliterate
import com.algolia.search.serialize.internal.KeyCamelCaseAttributes
import com.algolia.search.serialize.internal.KeyCustomNormalization
import com.algolia.search.serialize.internal.KeyCustomRanking
import com.algolia.search.serialize.internal.KeyDecompoundQuery
import com.algolia.search.serialize.internal.KeyDecompoundedAttributes
import com.algolia.search.serialize.internal.KeyDisableExactOnAttributes
import com.algolia.search.serialize.internal.KeyDisablePrefixOnAttributes
import com.algolia.search.serialize.internal.KeyDisableTypoToleranceOnAttributes
import com.algolia.search.serialize.internal.KeyDisableTypoToleranceOnWords
import com.algolia.search.serialize.internal.KeyDistinct
import com.algolia.search.serialize.internal.KeyEnablePersonalization
import com.algolia.search.serialize.internal.KeyEnableRules
import com.algolia.search.serialize.internal.KeyExactOnSingleWordQuery
import com.algolia.search.serialize.internal.KeyExactPhrase
import com.algolia.search.serialize.internal.KeyExcludeWords
import com.algolia.search.serialize.internal.KeyFacetOrdering
import com.algolia.search.serialize.internal.KeyFacets
import com.algolia.search.serialize.internal.KeyHighlightPostTag
import com.algolia.search.serialize.internal.KeyHighlightPreTag
import com.algolia.search.serialize.internal.KeyHitsPerPage
import com.algolia.search.serialize.internal.KeyIgnorePlurals
import com.algolia.search.serialize.internal.KeyIndexLanguages
import com.algolia.search.serialize.internal.KeyKeepDiacriticsOnCharacters
import com.algolia.search.serialize.internal.KeyMaxFacetHits
import com.algolia.search.serialize.internal.KeyMaxValuesPerFacet
import com.algolia.search.serialize.internal.KeyMinProximity
import com.algolia.search.serialize.internal.KeyMinWordSizeFor1Typo
import com.algolia.search.serialize.internal.KeyMinWordSizeFor2Typos
import com.algolia.search.serialize.internal.KeyNumericAttributesForFiltering
import com.algolia.search.serialize.internal.KeyOptionalWords
import com.algolia.search.serialize.internal.KeyOrder
import com.algolia.search.serialize.internal.KeyPaginationLimitedTo
import com.algolia.search.serialize.internal.KeyPrimary
import com.algolia.search.serialize.internal.KeyQueryLanguages
import com.algolia.search.serialize.internal.KeyQueryType
import com.algolia.search.serialize.internal.KeyRanking
import com.algolia.search.serialize.internal.KeyRelevancyStrictness
import com.algolia.search.serialize.internal.KeyRemoveStopWords
import com.algolia.search.serialize.internal.KeyRemoveWordsIfNoResults
import com.algolia.search.serialize.internal.KeyRenderingContent
import com.algolia.search.serialize.internal.KeyReplaceSynonymsInHighlight
import com.algolia.search.serialize.internal.KeyReplicas
import com.algolia.search.serialize.internal.KeyResponseFields
import com.algolia.search.serialize.internal.KeyRestrictHighlightAndSnippetArrays
import com.algolia.search.serialize.internal.KeySearchableAttributes
import com.algolia.search.serialize.internal.KeySeparatorsToIndex
import com.algolia.search.serialize.internal.KeySnippetEllipsisText
import com.algolia.search.serialize.internal.KeySortFacetValuesBy
import com.algolia.search.serialize.internal.KeySortRemainingBy
import com.algolia.search.serialize.internal.KeyTypoTolerance
import com.algolia.search.serialize.internal.KeyUnretrievableAttributes
import com.algolia.search.serialize.internal.KeyUserData
import com.algolia.search.serialize.internal.KeyValues
import com.algolia.search.serialize.internal.KeyVersion
import com.algolia.search.serialize.internal.toJsonNoDefaults
import indexA
import int
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
            attributesToTransliterate = attributes,
            renderingContent = RenderingContent(
                facetOrdering = FacetOrdering(
                    facets = FacetsOrder(order = listOf(unknown)),
                    values = mapOf(
                        attributeA to FacetValuesOrder(
                            order = listOf(string),
                            sortRemainingBy = SortRule.Alpha
                        )
                    )
                )
            )
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
            put(KeyDisablePrefixOnAttributes, attributesJson)
            put(KeyDisableExactOnAttributes, attributesJson)
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
            put(KeyIndexLanguages, buildJsonArray { add(Language.Japanese.raw) })
            put(KeyCustomNormalization, buildJsonObject { put(unknown, buildJsonObject { put(unknown, unknown) }) })
            put(KeyEnablePersonalization, boolean)
            put(KeyAttributeCriteriaComputedByMinProximity, boolean)
            put(KeyRelevancyStrictness, int)
            put(KeyDecompoundQuery, boolean)
            put(KeyAttributesToTransliterate, attributesJson)
            put(
                KeyRenderingContent,
                buildJsonObject {
                    put(
                        KeyFacetOrdering,
                        buildJsonObject {
                            put(
                                KeyFacets,
                                buildJsonObject {
                                    put(KeyOrder, buildJsonArray { add(unknown) })
                                }
                            )
                            put(
                                KeyValues,
                                buildJsonObject {
                                    put(
                                        attributeA.raw,
                                        buildJsonObject {
                                            put(KeyOrder, buildJsonArray { add(string) })
                                            put(KeySortRemainingBy, KeyAlpha)
                                        }
                                    )
                                }
                            )
                        }
                    )
                }
            )
            put(KeyPrimary, JsonNull)
        }
    )

    @Test
    fun encodeNoNull() {
        Settings().toJsonNoDefaults().toString() shouldEqual "{}"
    }
}
