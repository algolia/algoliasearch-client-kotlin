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
import com.algolia.search.serialize.internal.Key
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
            put(Key.SearchableAttributes, attributesJson)
            put(Key.AttributesForFaceting, attributesJson)
            put(Key.UnretrievableAttributes, attributesJson)
            put(Key.AttributesToRetrieve, attributesJson)
            // RankingCriterion
            put(Key.Ranking, buildJsonArray { add(RankingCriterion.Geo.raw) })
            put(Key.CustomRanking, buildJsonArray { add(CustomRankingCriterion.Asc(attributeA).raw) })
            put(Key.Replicas, buildJsonArray { add(indexA.raw) })
            // Faceting
            put(Key.MaxValuesPerFacet, int)
            put(Key.SortFacetValuesBy, SortFacetsBy.Count.raw)
            // Highlighting
            put(Key.AttributesToHighlight, attributesJson)
            put(Key.AttributesToSnippet, buildJsonArray { add(TestSnippet.json) })
            put(Key.HighlightPreTag, string)
            put(Key.HighlightPostTag, string)
            put(Key.SnippetEllipsisText, string)
            put(Key.RestrictHighlightAndSnippetArrays, boolean)
            // Pagination
            put(Key.HitsPerPage, int)
            put(Key.PaginationLimitedTo, int)
            put(Key.MinWordSizeFor1Typo, int)
            put(Key.MinWordSizeFor2Typos, int)
            // Typos
            put(Key.TypoTolerance, TypoTolerance.Min.raw)
            put(Key.AllowTyposOnNumericTokens, boolean)
            put(Key.DisableTypoToleranceOnAttributes, attributesJson)
            put(Key.DisableTypoToleranceOnWords, buildJsonArray { add(string) })
            put(Key.SeparatorsToIndex, string)
            // Languages
            put(Key.IgnorePlurals, boolean)
            put(Key.RemoveStopWords, boolean)
            put(Key.CamelCaseAttributes, attributesJson)
            put(
                Key.DecompoundedAttributes,
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
            put(Key.KeepDiacriticsOnCharacters, string)
            put(
                Key.QueryLanguages,
                buildJsonArray {
                    add(Language.Afrikaans.raw)
                    add(Language.Albanian.raw)
                }
            )
            // Query-rules
            put(Key.EnableRules, boolean)
            // Query-strategy
            put(Key.QueryType, QueryType.PrefixLast.raw)
            put(Key.RemoveWordsIfNoResults, RemoveWordIfNoResults.LastWords.raw)
            put(Key.AdvancedSyntax, boolean)
            put(
                Key.AdvancedSyntaxFeatures,
                buildJsonArray {
                    add(Key.ExcludeWords)
                    add(Key.ExactPhrase)
                }
            )
            put(Key.OptionalWords, buildJsonArray { add(string) })
            put(Key.DisablePrefixOnAttributes, attributesJson)
            put(Key.DisableExactOnAttributes, attributesJson)
            put(Key.ExactOnSingleWordQuery, ExactOnSingleWordQuery.Word.raw)
            put(Key.AlternativesAsExact, buildJsonArray { add(AlternativesAsExact.IgnorePlurals.raw) })
            // Performance
            put(Key.NumericAttributesForFiltering, buildJsonArray { add(TestNumericAttribute.json) })
            put(Key.AllowCompressionOfIntegerArray, boolean)
            // Advanced
            put(Key.AttributeForDistinct, attributeA.raw)
            put(Key.Distinct, int)
            put(Key.ReplaceSynonymsInHighlight, boolean)
            put(Key.MinProximity, int)
            put(Key.ResponseFields, buildJsonArray { add(ResponseFields.NbHits.raw) })
            put(Key.MaxFacetHits, int)
            put(Key.Version, int)
            put(Key.UserData, buildJsonObject { put(unknown, unknown) })
            put(Key.IndexLanguages, buildJsonArray { add(Language.Japanese.raw) })
            put(Key.CustomNormalization, buildJsonObject { put(unknown, buildJsonObject { put(unknown, unknown) }) })
            put(Key.EnablePersonalization, boolean)
            put(Key.AttributeCriteriaComputedByMinProximity, boolean)
            put(Key.RelevancyStrictness, int)
            put(Key.DecompoundQuery, boolean)
            put(Key.AttributesToTransliterate, attributesJson)
            put(
                Key.RenderingContent,
                buildJsonObject {
                    put(
                        Key.FacetOrdering,
                        buildJsonObject {
                            put(
                                Key.Facets,
                                buildJsonObject {
                                    put(Key.Order, buildJsonArray { add(unknown) })
                                }
                            )
                            put(
                                Key.Values,
                                buildJsonObject {
                                    put(
                                        attributeA.raw,
                                        buildJsonObject {
                                            put(Key.Order, buildJsonArray { add(string) })
                                            put(Key.SortRemainingBy, Key.Alpha)
                                        }
                                    )
                                }
                            )
                        }
                    )
                }
            )
            put(Key.Primary, JsonNull)
        }
    )

    @Test
    fun encodeNoNull() {
        Settings().toJsonNoDefaults().toString() shouldEqual "{}"
    }
}
