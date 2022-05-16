package com.algolia.search.model.search

import com.algolia.search.ExperimentalAlgoliaClientAPI
import com.algolia.search.model.Attribute
import com.algolia.search.model.params.AnswersParameters
import com.algolia.search.model.params.CommonSearchParameters
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExperimentalAlgoliaClientAPI
@Serializable
public data class AnswersQuery(

    /**
     * The query for which to retrieve results.
     * Cannot be empty or spaces only.
     */
    @SerialName(Key.Query) override var query: String,

    /**
     * Engine default: ["en"]
     * The languages in the query.
     * Currently only supports `en`.
     */
    @SerialName(Key.QueryLanguages) var queryLanguages: List<Language>,

    /**
     * Engine default: ["*"]
     * Attributes to use for predictions.
     * If empty, we use all `searchableAttributes` to find answers.
     * Note: All your `attributesForPrediction` must be part of your `searchableAttributes`.
     */
    @SerialName(Key.AttributesForPrediction) var attributesForPrediction: List<Attribute>? = null,

    /**
     * Engine default: 10
     * Maximum number of answers to retrieve from the Answers Engine.
     * Cannot be greater than 1000.
     */
    @SerialName(Key.NbHits) val nbHits: Int? = null,

    /**
     * Engine default: 10
     * Maximum number of answers to retrieve from the Answers Engine.
     * Cannot be greater than 1000.
     */
    @SerialName(Key.Threshold) val threshold: Float? = null,

    /**
     * Algolia search parameters to use to fetch the hits.
     * Can be any search parameter, except:
     * - `attributesToSnippet`
     * - `hitsPerPage`
     * - `restrictSearchableAttributes`
     */
    @SerialName(Key.Params) val params: SearchParameters = SearchParameters(),
) : AnswersParameters, CommonSearchParameters by params
