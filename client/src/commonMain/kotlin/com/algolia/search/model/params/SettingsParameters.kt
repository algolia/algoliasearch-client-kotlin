package com.algolia.search.model.params

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.rule.RenderingContent
import com.algolia.search.model.search.Language
import com.algolia.search.model.settings.AttributeForFaceting
import com.algolia.search.model.settings.CustomRankingCriterion
import com.algolia.search.model.settings.DecompoundedAttributes
import com.algolia.search.model.settings.NumericAttributeFilter
import com.algolia.search.model.settings.RankingCriterion
import com.algolia.search.model.settings.SearchableAttribute
import kotlinx.serialization.json.JsonObject

public interface SettingsParameters : CommonParameters {

    /**
     * The complete list of attributes that will be used for searching.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/searchableAttributes/?language=kotlin]
     */
    public var searchableAttributes: List<SearchableAttribute>?

    /**
     * The complete list of attributes that will be used for faceting.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesForFaceting/?language=kotlin]
     */
    public var attributesForFaceting: List<AttributeForFaceting>?

    /**
     * List of attributes that cannot be retrieved at query time.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/unretrievableAttributes/?language=kotlin]
     */
    public var unretrievableAttributes: List<Attribute>?

    /**
     * Controls the way results are sorted.
     * Engine default: [[RankingCriterion.Typo], [RankingCriterion.Geo], [RankingCriterion.Words],
     * [RankingCriterion.Filters], [RankingCriterion.Proximity], [RankingCriterion.Attribute], [RankingCriterion.Exact],
     * [RankingCriterion.Custom]]
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/ranking/?language=kotlin]
     */

    public var ranking: List<RankingCriterion>?

    /**
     * Specifies the [CustomRankingCriterion].
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/customRanking/?language=kotlin]
     */

    public var customRanking: List<CustomRankingCriterion>?

    /**
     * Creates replicas, exact copies of an index.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/replicas/?language=kotlin]
     */

    public var replicas: List<IndexName>?

    /**
     * Set the maximum number of hits accessible via pagination.
     * Engine default: 1000
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/paginationlimitedto/?language=kotlin]
     */
    public var paginationLimitedTo: Int?

    /**
     * List of words on which you want to disable typo tolerance.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disableTypoToleranceOnWords/?language=kotlin]
     */
    public var disableTypoToleranceOnWords: List<String>?

    /**
     * Control which separators are indexed. Separators are all non-alphanumeric characters except space.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/separatorsToIndex/?language=kotlin]
     */
    public var separatorsToIndex: String?

    /**
     * List of [Attribute] on which to do a decomposition of camel case words.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/camelCaseAttributes/?language=kotlin]
     */
    public var camelCaseAttributes: List<Attribute>?

    /**
     * Specify on which [Attribute] in your index Algolia should apply word-splitting (“decompounding”).
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/decompoundedAttributes/?language=kotlin]
     */
    public var decompoundedAttributes: List<DecompoundedAttributes>?

    /**
     * Characters that should not be automatically normalized by the search engine.
     * Engine default: "&quot;&quot;"
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/keepDiacriticsOnCharacters/?language=kotlin]
     */
    public var keepDiacriticsOnCharacters: String?

    /**
     * List of [Attribute] on which you want to disable prefix matching.
     * Engine default: []
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/disablePrefixOnAttributes/?language=kotlin]
     */
    public var disablePrefixOnAttributes: List<Attribute>?

    /**
     * List of [NumericAttributeFilter] that can be used as numerical filters.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/numericAttributesForFiltering/?language=kotlin]
     */
    public var numericAttributesForFiltering: List<NumericAttributeFilter>?

    /**
     * Enables compression of large integer arrays.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/allowCompressionOfIntegerArray/?language=kotlin]
     */
    public var allowCompressionOfIntegerArray: Boolean?

    /**
     * Name of the de-duplication [Attribute] to be used with the [distinct] feature.
     * Engine default: null
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributeForDistinct/?language=kotlin]
     */
    public var attributeForDistinct: Attribute?

    /**
     * Settings version.
     */
    public var version: Int?

    /**
     *  Lets you store custom data in your indices.
     */
    public var userData: JsonObject?

    /**
     * This parameter configures the segmentation of text at indexing time.
     * Accepted value: [Language.Japanese]
     * Input data to index is treated as the given language(s) for segmentation.
     */
    public var indexLanguages: List<Language>?

    /**
     * Override the custom normalization handled by the engine.
     */
    public var customNormalization: Map<String, Map<String, String>>?

    /**
     * Enable the Personalization feature.
     * Engine default: false
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/enablePersonalization/?language=kotlin]
     */
    public var enablePersonalization: Boolean

    /**
     * When `attribute` is ranked above `proximity` in your ranking formula, `proximity` is used to select which
     * searchable attribute is matched in the **attribute ranking stage**.
     * Engine default: false
     * [Documentation](https://www.algolia.com/doc/api-reference/api-parameters/attributeCriteriaComputedByMinProximity/?language=kotlin)
     */
    public var attributeCriteriaComputedByMinProximity: Boolean?

    /**
     * Specify on which attributes to apply transliteration. Transliteration refers to the ability of finding results in
     * a given alphabet with a query in another alphabet. For example, in Japanese, transliteration enables users to
     * find results indexed in Kanji or Katakana with a query in Hiragana.
     * Engine default: [*]
     * [Documentation](https://www.algolia.com/doc/api-reference/api-parameters/attributesToTransliterate/?language=kotlin)
     */
    public var attributesToTransliterate: List<Attribute>?

    /**
     * Content defining how the search interface should be rendered.
     * This is set via the settings for a default value and can be overridden via rules
     */
    public var renderingContent: RenderingContent?
}
