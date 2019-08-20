package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyAlternatives
import com.algolia.search.serialize.KeyAnchoring
import com.algolia.search.serialize.KeyContext
import com.algolia.search.serialize.KeyPattern
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class Condition(
    @SerialName(KeyAnchoring) val anchoring: Anchoring,
    @SerialName(KeyPattern) val pattern: Pattern,
    /**
     * Rule context (format: [A-Za-z0-9_-]+). When specified, the rule is contextual and applies only when the
     * same context is specified at query time (using the ruleContexts parameter). When absent, the rule is generic
     * and always applies (provided that its other conditions are met, of course).
     */
    @SerialName(KeyContext) val context: String? = null,
    /**
     *  Indicates if the rule can be applied with alternatives.
     */
    @SerialName(KeyAlternatives) val alternative: Alternatives? = null
)