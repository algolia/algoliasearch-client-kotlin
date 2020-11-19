package com.algolia.search.model.answers

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.Language
import com.algolia.search.serialize.KeyAttributesForPrediction
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyQueryLanguages
import com.algolia.search.serialize.KeyThreshold
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class AnswersQuery(

    /**
     * The query for which to retrieve results.
     * Cannot be empty or spaces only.
     */
    @SerialName(KeyQuery) var query: String,

    /**
     * Engine default: ["en"]
     * The languages in the query.
     * Currently only supports `en`.
     */
    @SerialName(KeyQueryLanguages) var queryLanguages: List<Language>? = null,

    /**
     * Algolia search parameters to use to fetch the hits.
     * Can be any search parameter, except:
     * - `attributesToSnippet`
     * - `hitsPerPage`
     * - `restrictSearchableAttributes`
     */
    @SerialName(KeyParams) var params: JsonObject? = null, // TODO: RequestParams?

    /**
     * Engine default: ["*"]
     * Attributes to use for predictions.
     * If empty, we use all `searchableAttributes` to find answers.
     * Note: All your `attributesForPrediction` must be part of your `searchableAttributes`.
     */
    @SerialName(KeyAttributesForPrediction) var attributesForPrediction: List<Attribute>? = null,

    /**
     * Engine default: 10
     * Maximum number of answers to retrieve from the Answers Engine.
     * Cannot be greater than 1000.
     */
    @SerialName(KeyNbHits) val nbHits: Int? = null,

    /**
     * Engine default: 10
     * Maximum number of answers to retrieve from the Answers Engine.
     * Cannot be greater than 1000.
     */
    @SerialName(KeyThreshold) val threshold: Float? = null,
)
