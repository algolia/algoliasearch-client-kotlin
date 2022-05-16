package com.algolia.search.model.search

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.params.SearchParameters
import com.algolia.search.model.settings.AdvancedSyntaxFeatures
import com.algolia.search.model.settings.Distinct
import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.KSerializerPoint
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
@DSLParameters
public data class RecommendSearchOptions(
    /**
     * The text to search in the index.
     * Engine default: ""
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/query/?language=kotlin]
     */
    @SerialName(Key.Query) override var query: String? = null,

    /**
     * Gives control over which attributes to retrieve and which not to retrieve.
     * Engine default: [*]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToRetrieve/?language=kotlin]
     */
    @SerialName(Key.AttributesToRetrieve) override var attributesToRetrieve: List<Attribute>? = null,

    /**
     * Restricts a given query to look in only a subset of your searchable attributes.
     * Engine default: all attributes in [Settings.searchableAttributes].
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictSearchableAttributes/?language=kotlin]
     */
    @SerialName(Key.RestrictSearchableAttributes) override var restrictSearchableAttributes: List<Attribute>? = null,

    /**
     * Filter the query with numeric, facet and/or tag filters.
     * Engine default: ""
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/?language=kotlin]
     */
    @SerialName(Key.Filters) override var filters: String? = null,

    /**
     * Filter hits by facet value.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetFilters/?language=kotlin]
     */
    @SerialName(Key.FacetFilters) override var facetFilters: List<List<String>>? = null,

    /**
     * Create filters for ranking purposes, where records that match the filter are ranked highest.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalFilters/?language=kotlin]
     */
    @SerialName(Key.OptionalFilters) override var optionalFilters: List<List<String>>? = null,

    /**
     * Filter on numeric attributes.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/numericFilters/?language=kotlin]
     */
    @SerialName(Key.NumericFilters) override var numericFilters: List<List<String>>? = null,

    /**
     * Filter hits by tags.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/tagFilters/?language=kotlin]
     */
    @SerialName(Key.TagFilters) override var tagFilters: List<List<String>>? = null,

    /**
     * Determines how to calculate the total score for filtering.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sumOrFiltersScores/?language=kotlin]
     */
    @SerialName(Key.SumOrFiltersScores) override var sumOrFiltersScores: Boolean? = null,

    /**
     * Facets to retrieve.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facets/?language=kotlin]
     */
    @SerialName(Key.Facets) override var facets: Set<Attribute>? = null,

    /**
     * Maximum number of facet values to return for each facet during a regular search.
     * Engine default: 100
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxValuesPerFacet/?language=kotlin]
     */
    @SerialName(Key.MaxValuesPerFacet) override var maxValuesPerFacet: Int? = null,

    /**
     * Force faceting to be applied after de-duplication (via the Distinct setting).
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/facetingAfterDistinct/?language=kotlin]
     */
    @SerialName(Key.FacetingAfterDistinct) override var facetingAfterDistinct: Boolean? = null,

    /**
     * Controls how facet values are sorted.
     * Engine default: [SortFacetsBy.Count]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/sortFacetValuesBy/?language=kotlin]
     */
    @SerialName(Key.SortFacetValuesBy) override var sortFacetsBy: SortFacetsBy? = null,

    /**
     * List of attributes to highlight.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToHighlight/?language=kotlin]
     */
    @SerialName(Key.AttributesToHighlight) override var attributesToHighlight: List<Attribute>? = null,

    /**
     * List of attributes to snippet, with an optional maximum number of words to snippet.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToSnippet/?language=kotlin]
     */
    @SerialName(Key.AttributesToSnippet) override var attributesToSnippet: List<Snippet>? = null,

    /**
     * The HTML name to insert before the highlighted parts in all highlight and snippet results.
     * Engine default: <em>
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPreTag/?language=kotlin]
     */
    @SerialName(Key.HighlightPreTag) override var highlightPreTag: String? = null,

    /**
     * The HTML name to insert after the highlighted parts in all highlight and snippet results.
     * Engine default: </em>
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/highlightPostTag/?language=kotlin]
     */
    @SerialName(Key.HighlightPostTag) override var highlightPostTag: String? = null,

    /**
     * String used as an ellipsis indicator when a snippet is truncated.
     * Engine default: "…" (U+2026, HORIZONTAL ELLIPSIS)
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/snippetEllipsisText/?language=kotlin]
     */
    @SerialName(Key.SnippetEllipsisText) override var snippetEllipsisText: String? = null,

    /**
     * Restrict highlighting and snippeting to items that matched the query.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/restrictHighlightAndSnippetArrays/?language=kotlin]
     */
    @SerialName(Key.RestrictHighlightAndSnippetArrays) override var restrictHighlightAndSnippetArrays: Boolean? = null,

    /**
     * Minimum number of characters a word in the query name must contain to accept matches with 1 typo.
     * Engine default: 4
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor1Typo/?language=kotlin]
     */
    @SerialName(Key.MinWordSizeFor1Typo) override var minWordSizeFor1Typo: Int? = null,

    /**
     * Minimum number of characters a word in the query name must contain to accept matches with 2 typos.
     * Engine default: 8
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minWordSizefor2Typos/?language=kotlin]
     */
    @SerialName(Key.MinWordSizeFor2Typos) override var minWordSizeFor2Typos: Int? = null,

    /**
     * Controls whether typo tolerance is enabled and how it is applied.
     * Engine defaults: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/typoTolerance/?language=kotlin]
     */
    @SerialName(Key.TypoTolerance) override var typoTolerance: TypoTolerance? = null,

    /**
     * Whether to allow typos on numbers (“numeric tokens”) in the query name.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/allowTyposOnNumericTokens/?language=kotlin]
     */
    @SerialName(Key.AllowTyposOnNumericTokens) override var allowTyposOnNumericTokens: Boolean? = null,

    /**
     * List of attributes on which you want to disable typo tolerance.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableTypoToleranceOnAttributes/?language=kotlin]
     */
    @SerialName(Key.DisableTypoToleranceOnAttributes) override var disableTypoToleranceOnAttributes: List<Attribute>? = null,

    /**
     * Search for entries around a central geolocation, enabling a geo search within a circular area.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLng/?language=kotlin]
     */
    @SerialName(Key.AroundLatLng) @Serializable(KSerializerPoint::class) override var aroundLatLng: Point? = null,

    /**
     * Whether to search entries around a given location automatically computed from the requester’s IP address.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundLatLngViaIP/?language=kotlin]
     */
    @SerialName(Key.AroundLatLngViaIP) override var aroundLatLngViaIP: Boolean? = null,

    /**
     * Define the maximum radius for a geo search (in meters).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundRadius/?language=kotlin]
     */
    @SerialName(Key.AroundRadius) override var aroundRadius: AroundRadius? = null,

    /**
     * Precision of geo search (in meters), to add grouping by geo location to the ranking formula.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/aroundPrecision/?language=kotlin]
     */
    @SerialName(Key.AroundPrecision) override var aroundPrecision: AroundPrecision? = null,

    /**
     * Minimum radius (in meters) used for a geo search when [aroundRadius] is not set.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minimumAroundRadius/?language=kotlin]
     */
    @SerialName(Key.MinimumAroundRadius) override var minimumAroundRadius: Int? = null,

    /**
     * Search inside a rectangular area (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insideBoundingBox/?language=kotlin]
     */
    @SerialName(Key.InsideBoundingBox) override var insideBoundingBox: List<BoundingBox>? = null,

    /**
     * Search inside a polygon (in geo coordinates).
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/insidePolygon/?language=kotlin]
     */
    @SerialName(Key.InsidePolygon) override var insidePolygon: List<Polygon>? = null,

    /**
     * Treats singular, plurals, and other forms of declensions as matching terms.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ignorePlurals/?language=kotlin]
     */
    @SerialName(Key.IgnorePlurals) override var ignorePlurals: IgnorePlurals? = null,

    /**
     * Removes stop (task) words from the query before executing it.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeStopWords/?language=kotlin]
     */
    @SerialName(Key.RemoveStopWords) override var removeStopWords: RemoveStopWords? = null,

    /**
     * Sets the queryLanguage to be used by language-specific settings and functionalities such as
     * [ignorePlurals], [removeStopWords], and
     * [CJK word-detection][https://www.algolia.com/doc/guides/textual-relevance/queryLanguage/#using-a-language-specific-dictionary-for-cjk-words].
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryLanguages/?language=kotlin]
     */
    @SerialName(Key.QueryLanguages) override var queryLanguages: List<Language>? = null,

    /**
     * Whether rules should be globally enabled.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enableRules/?language=kotlin]
     */
    @SerialName(Key.EnableRules) override var enableRules: Boolean? = null,

    /**
     * Enables contextual rules.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ruleContexts/?language=kotlin]
     */
    @SerialName(Key.RuleContexts) override var ruleContexts: List<String>? = null,

    /**
     * Enable the Personalization feature.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enablePersonalization/?language=kotlin]
     */
    @SerialName(Key.EnablePersonalization) override var enablePersonalization: Boolean? = null,

    /**
     *  The `personalizationImpact` parameter sets the percentage of the impact that personalization has on ranking
     *  records.
     *  This is set at query time and therefore overrides any impact value you had set on your index.
     *  The higher the `personalizationImpact`, the more the results are personalized for the user, and the less the
     *  custom ranking is taken into account in ranking records.
     *
     *  Usage note:
     *
     *  - The value must be between 0 and 100 (inclusive).
     *  - This parameter isn't taken into account if `enablePersonalization` is `false`.
     *  - Setting `personalizationImpact` to `0` disables the Personalization feature, as if `enablePersonalization`
     *    were `false`.
     */
    @SerialName(Key.PersonalizationImpact) override var personalizationImpact: Int? = null,

    /**
     * Associates a certain user token with the current search.
     * Engine default: User ip address
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/userToken/?language=kotlin]
     */
    @SerialName(Key.UserToken) override var userToken: UserToken? = null,

    /**
     * Controls if and how query words are interpreted as [prefixes][https://www.algolia.com/doc/guides/textual-relevance/prefix-search/?language=kotlin].
     * Engine default: [QueryType.PrefixLast]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/queryType/?language=kotlin]
     */
    @SerialName(Key.QueryType) override var queryType: QueryType? = null,

    /**
     * Selects a strategy to remove words from the query when it doesn’t match any hits.
     * Engine default: [RemoveWordIfNoResults.None]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/removeWordsIfNoResults/?language=kotlin]
     */
    @SerialName(Key.RemoveWordsIfNoResults) override var removeWordsIfNoResults: RemoveWordIfNoResults? = null,

    /**
     * Enables the advanced query syntax.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/advancedSyntax/?language=kotlin]
     */
    @SerialName(Key.AdvancedSyntax) override var advancedSyntax: Boolean? = null,

    /**
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters//?language=kotlin]
     */
    @SerialName(Key.AdvancedSyntaxFeatures) override var advancedSyntaxFeatures: List<AdvancedSyntaxFeatures>? = null,

    /**
     * A list of words that should be considered as optional when found in the query.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/optionalWords/?language=kotlin]
     */
    @SerialName(Key.OptionalWords) override var optionalWords: List<String>? = null,

    /**
     * List of attributes on which you want to disable the exact ranking criterion.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableExactOnAttributes/?language=kotlin]
     */
    @SerialName(Key.DisableExactOnAttributes) override var disableExactOnAttributes: List<Attribute>? = null,

    /**
     * Controls how the exact ranking criterion is computed when the query contains only one word.
     * Engine default: [ExactOnSingleWordQuery.Attribute]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/exactOnSingleWordQuery/?language=kotlin]
     */
    @SerialName(Key.ExactOnSingleWordQuery) override var exactOnSingleWordQuery: ExactOnSingleWordQuery? = null,

    /**
     * List of alternatives that should be considered an exact match by the exact ranking criterion.
     * Engine default: [[AlternativesAsExact.IgnorePlurals], [AlternativesAsExact.SingleWordSynonym]]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/alternativesAsExact/?language=kotlin]
     */
    @SerialName(Key.AlternativesAsExact) override var alternativesAsExact: List<AlternativesAsExact>? = null,

    /**
     * Enables de-duplication or grouping of results.
     * Engine default: 0
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/distinct/?language=kotlin]
     */
    @SerialName(Key.Distinct) override var distinct: Distinct? = null,

    /**
     * Retrieve detailed ranking information.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/getRankingInfo/?language=kotlin]
     */
    @SerialName(Key.GetRankingInfo) override var getRankingInfo: Boolean? = null,

    /**
     * Enable the Click Analytics feature.
     * Engine default: false.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/clickAnalytics/?language=kotlin]
     */
    @SerialName(Key.ClickAnalytics) override var clickAnalytics: Boolean? = null,

    /**
     * Whether the current query will be taken into account in the Analytics.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/analytics/?language=kotlin]
     */
    @SerialName(Key.Analytics) override var analytics: Boolean? = null,

    /**
     * List of tags to apply to the query in the analytics.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/analyticsTags/?language=kotlin]
     */
    @SerialName(Key.AnalyticsTags) override var analyticsTags: List<String>? = null,

    /**
     * Whether to take into account an index’s synonyms for a particular search.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/synonyms/?language=kotlin]
     */
    @SerialName(Key.Synonyms) override var synonyms: Boolean? = null,

    /**
     * Whether to highlight and snippet the original word that matches the synonym or the synonym itself.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/replaceSynonymsInHighlight/?language=kotlin]
     */
    @SerialName(Key.ReplaceSynonymsInHighlight) override var replaceSynonymsInHighlight: Boolean? = null,

    /**
     * Precision of the proximity ranking criterion.
     * Engine default: 1
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/minProximity/?language=kotlin]
     */
    @SerialName(Key.MinProximity) override var minProximity: Int? = null,

    /**
     * Choose which fields the response will contain. Applies to search and browse queries.
     * Engine default: [ResponseFields.All]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/responseFields/?language=kotlin]
     */
    @SerialName(Key.ResponseFields) override var responseFields: List<ResponseFields>? = null,

    /**
     * Maximum number of facet hits to return during a search for facet values.
     * Engine default: 10
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/maxFacetHits/?language=kotlin]
     */
    @SerialName(Key.MaxFacetHits) override var maxFacetHits: Int? = null,

    /**
     * Whether to include or exclude a query from the processing-time percentile computation.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/percentileComputation/?language=kotlin]
     */
    @SerialName(Key.PercentileComputation) override var percentileComputation: Boolean? = null,

    /**
     *  Overrides the query parameter and performs a more generic search that can be used to find "similar" results.
     *  Engine default: ""
     *  [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/similarQuery/?language=kotlin]
     */
    @SerialName(Key.SimilarQuery) override var similarQuery: String? = null,

    /**
     * Whether this query should be taken into consideration by currently active ABTests.
     * Engine default: true
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enableABTest/?language=kotlin]
     */
    @SerialName(Key.EnableABTest) override var enableABTest: Boolean? = null,

    /**
     * Enriches the API’s response with meta-information as to how the query was processed.
     * It is possible to enable several [ExplainModule] independently.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/decompoundedAttributes/?language=kotlin]
     */
    @SerialName(Key.Explain) override var explainModules: List<ExplainModule>? = null,

    /**
     * List of supported languages with their associated language ISO code.
     * Provide an easy way to implement voice and natural languages best practices such as ignorePlurals,
     * removeStopWords, removeWordsIfNoResults, analyticsTags and ruleContexts.
     */
    @SerialName(Key.NaturalLanguages) override var naturalLanguages: List<Language>? = null,

    /**
     * Relevancy score to apply to search in virtual index [0-100]. Bigger value means less, but more relevant results,
     * lesser value - less relevant results.
     */
    @SerialName(Key.RelevancyStrictness) override var relevancyStrictness: Int? = null,

    /**
     * Enable word segmentation (also called decompounding) at query time for compatible languages. For example, this
     * turns the Dutch query "spaanplaatbehang" into "spaan plaat behang" to retrieve more relevant results.
     * Engine default: true
     * [Documentation](https://www.algolia.com/doc/api-reference/api-parameters/decompoundQuery/?client=kotlin)
     */
    @SerialName(Key.DecompoundQuery) override var decompoundQuery: Boolean? = null,

    /**
     * You need to turn on Dynamic Re-Ranking on your index for it to have an effect on your search results.
     * You can do this through the Re-Ranking page on the dashboard. This parameter is only used to turn off Dynamic
     * Re-Ranking (with false) at search time.
     */
    @SerialName(Key.EnableReRanking) override var enableReRanking: Boolean? = null,
) : SearchParameters {

    @Transient
    override var hitsPerPage: Int? = null

    @Transient
    override var page: Int? = null

    @Transient
    override var offset: Int? = null

    @Transient
    override var length: Int? = null
}
