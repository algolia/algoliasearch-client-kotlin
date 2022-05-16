package com.algolia.search.model.rule

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Rule(
    /**
     * Unique identifier for the rule.
     */
    @SerialName(Key.ObjectID) val objectID: ObjectID,
    /**
     * Conditions of the rule.
     */
    @SerialName(Key.Conditions) val conditions: List<Condition>? = null,
    /**
     * Consequence of the rule.
     */
    @SerialName(Key.Consequence) @Serializable(Consequence.Companion::class) val consequence: Consequence,
    /**
     * Whether the rule is enabled. Disabled rules remain in the index, but are not applied at query time.
     */
    @SerialName(Key.Enabled) val enabled: Boolean? = null,
    /**
     * By default, rules are permanently valid
     * When validity periods are specified, the rule applies only during those periods;
     * it is ignored the rest of the time. The list must not be empty.
     */
    @SerialName(Key.Validity) val validity: List<TimeRange>? = null,
    /**
     * This field is intended for rule management purposes, in particular to ease searching for rules and
     * presenting them to human readers. It is not interpreted by the API.
     */
    @SerialName(Key.Description) val description: String? = null
)
