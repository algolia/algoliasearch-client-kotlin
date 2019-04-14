package com.algolia.search.model.settings

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.*
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@DSLParameters
public data class Settings(
    @SerialName(KeySearchableAttributes) var searchableAttributes: List<SearchableAttribute>? = null,
    @SerialName(KeyAttributesForFaceting) var attributesForFaceting: List<AttributeForFaceting>? = null,
    @SerialName(KeyUnretrievableAttributes) var unretrievableAttributes: List<Attribute>? = null,
    @SerialName(KeyAttributesToRetrieve) var attributesToRetrieve: List<Attribute>? = null,
    @SerialName(KeyRanking) var ranking: List<RankingCriterion>? = null,
    @SerialName(KeyCustomRanking) var customRanking: List<CustomRankingCriterion>? = null,
    @SerialName(KeyReplicas) var replicas: List<IndexName>? = null,
    @SerialName(KeyMaxValuesPerFacet) var maxValuesPerFacet: Int? = null,
    @SerialName(KeySortFacetValuesBy) var sortFacetsBy: SortFacetsBy? = null,
    @SerialName(KeyAttributesToHighlight) var attributesToHighlight: List<Attribute>? = null,
    @SerialName(KeyAttributesToSnippet) var attributesToSnippet: List<Snippet>? = null,
    @SerialName(KeyHighlightPreTag) var highlightPreTag: String? = null,
    @SerialName(KeyHighlightPostTag) var highlightPostTag: String? = null,
    @SerialName(KeySnippetEllipsisText) var snippetEllipsisText: String? = null,
    @SerialName(KeyRestrictHighlightAndSnippetArrays) var restrictHighlightAndSnippetArrays: Boolean? = null,
    @SerialName(KeyHitsPerPage) var hitsPerPage: Int? = null,
    @SerialName(KeyPaginationLimitedTo) var paginationLimitedTo: Int? = null,
    @SerialName(KeyMinWordSizeFor1Typo) var minWordSizeFor1Typo: Int? = null,
    @SerialName(KeyMinWordSizeFor2Typos) var minWordSizeFor2Typos: Int? = null,
    @SerialName(KeyTypoTolerance) var typoTolerance: TypoTolerance? = null,
    @SerialName(KeyAllowTyposOnNumericTokens) var allowTyposOnNumericTokens: Boolean? = null,
    @SerialName(KeyDisableTypoToleranceOnAttributes) var disableTypoToleranceOnAttributes: List<Attribute>? = null,
    @SerialName(KeyDisableTypoToleranceOnWords) var disableTypoToleranceOnWords: List<String>? = null,
    @SerialName(KeySeparatorsToIndex) var separatorsToIndex: String? = null,
    @SerialName(KeyIgnorePlurals) var ignorePlurals: IgnorePlurals? = null,
    @SerialName(KeyRemoveStopWords) var removeStopWords: RemoveStopWords? = null,
    @SerialName(KeyCamelCaseAttributes) var camelCaseAttributes: List<Attribute>? = null,
    @SerialName(KeyDecompoundedAttributes) var decompoundedAttributes: List<DecompoundedAttributes>? = null,
    @SerialName(KeyKeepDiacriticsOnCharacters) var keepDiacriticsOnCharacters: String? = null,
    @SerialName(KeyQueryLanguages) var queryLanguages: List<QueryLanguage>? = null,
    @SerialName(KeyEnableRules) var enableRules: Boolean? = null,
    @SerialName(KeyQueryType) var queryType: QueryType? = null,
    @SerialName(KeyRemoveWordsIfNoResults) var removeWordsIfNoResults: RemoveWordIfNoResults? = null,
    @SerialName(KeyAdvancedSyntax) var advancedSyntax: Boolean? = null,
    @SerialName(KeyAdvancedSyntaxFeatures) var advancedSyntaxFeatures: List<AdvancedSyntaxFeatures>? = null,
    @SerialName(KeyOptionalWords) var optionalWords: List<String>? = null,
    @SerialName(KeyDisablePrefixOnAttributes) var disablePrefixOnAttributes: List<Attribute>? = null,
    @SerialName(KeyDisableExactOnAttributes) var disableExactOnAttributes: List<Attribute>? = null,
    @SerialName(KeyExactOnSingleWordQuery) var exactOnSingleWordQuery: ExactOnSingleWordQuery? = null,
    @SerialName(KeyAlternativesAsExact) var alternativesAsExact: List<AlternativesAsExact>? = null,
    @SerialName(KeyNumericAttributesForFiltering) var numericAttributesForFiltering: List<NumericAttributeFilter>? = null,
    @SerialName(KeyAllowCompressionOfIntegerArray) var allowCompressionOfIntegerArray: Boolean? = null,
    @SerialName(KeyAttributeForDistinct) var attributeForDistinct: Attribute? = null,
    @SerialName(KeyDistinct) var distinct: Distinct? = null,
    @SerialName(KeyReplaceSynonymsInHighlight) var replaceSynonymsInHighlight: Boolean? = null,
    @SerialName(KeyMinProximity) var minProximity: Int? = null,
    @SerialName(KeyResponseFields) var responseFields: List<ResponseFields>? = null,
    @SerialName(KeyMaxFacetHits) var maxFacetHits: Int? = null,
    @SerialName(KeyVersion) var version: Int? = null
)