package com.algolia.search.model.rule

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Condition(
    @SerialName(Key.Anchoring) val anchoring: Anchoring? = null,
    @SerialName(Key.Pattern) val pattern: Pattern? = null,
    /**
     * Rule context (format: [A-Za-z0-9_-]+). When specified, the rule is contextual and applies only when the
     * same context is specified at query time (using the ruleContexts parameter). When absent, the rule is generic
     * and always applies (provided that its other conditions are met, of course).
     */
    @SerialName(Key.Context) val context: String? = null,
    /**
     *  Indicates if the rule can be applied with alternatives.
     */
    @SerialName(Key.Alternatives) val alternative: Alternatives? = null,
    /**
     * Enables triggering rules based on applied facet and filter.
     */
    @SerialName(Key.Filters) val filters: String? = null,
)
