package com.algolia.search.model.settings

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.*
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Settings(
    @Optional @SerialName(KeySearchableAttributes) var searchableAttributes: List<SearchableAttribute>? = null,
    @Optional @SerialName(KeyAttributesForFaceting) var attributesForFaceting: List<AttributeForFaceting>? = null,
    @Optional @SerialName(KeyUnretrievableAttributes) var unretrievableAttributes: List<Attribute>? = null,
    @Optional @SerialName(KeyAttributesToRetrieve) var attributesToRetrieve: List<Attribute>? = null,
    @Optional @SerialName(KeyRanking) var ranking: List<RankingCriteria>? = null,
    @Optional @SerialName(KeyCustomRanking) var customRanking: List<CustomRankingCriteria>? = null,
    @Optional @SerialName(KeyReplicas) var replicas: List<IndexName>? = null,
    @Optional @SerialName(KeyMaxValuesPerFacet) var maxValuesPerFacet: Int? = null,
    @Optional @SerialName(KeySortFacetValuesBy) var sortFacetValuesBy: SortFacetValuesBy? = null,
    @Optional @SerialName(KeyAttributesToHighlight) var attributesToHighlight: List<Attribute>? = null,
    @Optional @SerialName(KeyAttributesToSnippet) var attributesToSnippet: List<Snippet>? = null,
    @Optional @SerialName(KeyHighlightPreTag) var highlightPreTag: String? = null,
    @Optional @SerialName(KeyHighlightPostTag) var highlightPostTag: String? = null,
    @Optional @SerialName(KeySnippetEllipsisText) var snippetEllipsisText: String? = null,
    @Optional @SerialName(KeyRestrictHighlightAndSnippetArrays) var restrictHighlightAndSnippetArrays: Boolean? = null,
    @Optional @SerialName(KeyHitsPerPage) var hitsPerPage: Int? = null,
    @Optional @SerialName(KeyPaginationLimitedTo) var paginationLimitedTo: Int? = null,
    @Optional @SerialName(KeyMinWordSizefor1Typo) var minWordSizeFor1Typo: Int? = null,
    @Optional @SerialName(KeyMinWordSizefor2Typos) var minWordSizeFor2Typos: Int? = null,
    @Optional @SerialName(KeyTypoTolerance) var typoTolerance: TypoTolerance? = null,
    @Optional @SerialName(KeyAllowTyposOnNumericTokens) var allowTyposOnNumericTokens: Boolean? = null,
    @Optional @SerialName(KeyDisableTypoToleranceOnAttributes) var disableTypoToleranceOnAttributes: List<Attribute>? = null,
    @Optional @SerialName(KeyDisableTypoToleranceOnWords) var disableTypoToleranceOnWords: List<String>? = null,
    @Optional @SerialName(KeySeparatorsToIndex) var separatorsToIndex: String? = null,
    @Optional @SerialName(KeyIgnorePlurals) var ignorePlurals: IgnorePlurals? = null,
    @Optional @SerialName(KeyRemoveStopWords) var removeStopWords: RemoveStopWords? = null,
    @Optional @SerialName(KeyCamelCaseAttributes) var camelCaseAttributes: List<Attribute>? = null,
    @Optional @SerialName(KeyDecompoundedAttributes) var decompoundedAttributes: List<DecompoundedAttributes>? = null,
    @Optional @SerialName(KeyKeepDiacriticsOnCharacters) var keepDiacriticsOnCharacters: String? = null,
    @Optional @SerialName(KeyQueryLanguages) var queryLanguages: List<QueryLanguage>? = null,
    @Optional @SerialName(KeyEnableRules) var enableRules: Boolean? = null,
    @Optional @SerialName(KeyQueryType) var queryType: QueryType? = null,
    @Optional @SerialName(KeyRemoveWordsIfNoResults) var removeWordsIfNoResults: RemoveWordIfNoResults? = null,
    @Optional @SerialName(KeyAdvancedSyntax) var advancedSyntax: Boolean? = null,
    @Optional @SerialName(KeyOptionalWords) var optionalWords: List<String>? = null,
    @Optional @SerialName(KeyDisablePrefixOnAttributes) var disablePrefixOnAttributes: List<Attribute>? = null,
    @Optional @SerialName(KeyDisableExactOnAttributes) var disableExactOnAttributes: List<Attribute>? = null,
    @Optional @SerialName(KeyExactOnSingleWordQuery) var exactOnSingleWordQuery: ExactOnSingleWordQuery? = null,
    @Optional @SerialName(KeyAlternativesAsExact) var alternativesAsExact: List<AlternativesAsExact>? = null,
    @Optional @SerialName(KeyNumericAttributesForFiltering) var numericAttributesForFiltering: List<NumericAttributeFilter>? = null,
    @Optional @SerialName(KeyAllowCompressionOfIntegerArray) var allowCompressionOfIntegerArray: Boolean? = null,
    @Optional @SerialName(KeyAttributeForDistinct) var attributeForDistinct: Attribute? = null,
    @Optional @SerialName(KeyDistinct) var distinct: Distinct? = null,
    @Optional @SerialName(KeyReplaceSynonymsInHighlight) var replaceSynonymsInHighlight: Boolean? = null,
    @Optional @SerialName(KeyMinProximity) var minProximity: Int? = null,
    @Optional @SerialName(KeyResponseFields) var responseFields: List<ResponseFields>? = null,
    @Optional @SerialName(KeyMaxFacetHits) var maxFacetHits: Int? = null,
    @Optional @SerialName(KeyVersion) var version: Int? = null
)