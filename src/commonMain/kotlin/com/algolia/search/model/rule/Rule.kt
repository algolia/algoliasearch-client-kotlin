package com.algolia.search.model.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.KeyCondition
import com.algolia.search.serialize.KeyConditions
import com.algolia.search.serialize.KeyConsequence
import com.algolia.search.serialize.KeyDescription
import com.algolia.search.serialize.KeyEnabled
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyValidity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Rule(
    /**
     * Unique identifier for the rule.
     */
    @SerialName(KeyObjectID) val objectID: ObjectID,
    /**
     * Condition of the rule.
     */
    @Deprecated("Single condition is deprecated, use Conditions (plural) which accept one or more condition(s)")
    @SerialName(KeyCondition) val condition: Condition? = null,
    /**
     * Consequence of the rule.
     */
    @SerialName(KeyConsequence) @Serializable(Consequence.Companion::class) val consequence: Consequence,
    /**
     * Whether the rule is enabled. Disabled rules remain in the index, but are not applied at query time.
     */
    @SerialName(KeyEnabled) val enabled: Boolean? = null,
    /**
     * By default, rules are permanently valid
     * When validity periods are specified, the rule applies only during those periods;
     * it is ignored the rest of the time. The list must not be empty.
     */
    @SerialName(KeyValidity) val validity: List<TimeRange>? = null,
    /**
     * This field is intended for rule management purposes, in particular to ease searching for rules and
     * presenting them to human readers. It is not interpreted by the API.
     */
    @SerialName(KeyDescription) val description: String? = null,
    /**
     * Conditions of the rule.
     */
    @SerialName(KeyConditions) val conditions: List<Condition>? = null
) {

    constructor(
        objectID: ObjectID,
        conditions: List<Condition>? = null,
        consequence: Consequence,
        enabled: Boolean? = null,
        validity: List<TimeRange>? = null,
        description: String? = null
    ) : this(
        objectID = objectID,
        condition = null,
        consequence = consequence,
        enabled = enabled,
        validity = validity,
        description = description,
        conditions = conditions
    )
}
