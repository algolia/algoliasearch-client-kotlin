package com.algolia.search.model.rule

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Suppress("PropertyName")
@DSLParameters
@Serializable
public data class RuleQuery(
    /**
     * Engine default: ""
     * Full text query.
     */
    @SerialName(Key.Query) var query: String? = null,
    /**
     * Engine default: null
     * When not null, restricts matches to rules with a specific [Anchoring]. Otherwise all [Anchoring] may match.
     */
    @SerialName(Key.Anchoring) var anchoring: Anchoring? = null,
    /**
     * Restricts matches to contextual rules with a specific context (exact match).
     */
    @SerialName(Key.Context) var context: String? = null,
    /**
     * Engine default: 0
     * Requested page.
     */
    @SerialName(Key.Page) var page: Int? = null,
    /**
     * Engine default: 20
     * Maximum number of hits in a page. Minimum is 1, maximum is 1000.
     */
    @SerialName(Key.HitsPerPage) var hitsPerPage: Int? = null,
    /**
     * Engine default: null
     * When specified, restricts matches to [Rule] with a specific [Rule.enabled].
     * When absent (default), all [Rule] are retrieved, regardless of their [Rule.enabled].
     */
    @SerialName(Key.Enabled) var enabled: Boolean? = null
) {

    @Transient
    public val Is: Anchoring.Is = Anchoring.Is

    @Transient
    public val StartsWith: Anchoring.StartsWith = Anchoring.StartsWith

    @Transient
    public val EndsWith: Anchoring.EndsWith = Anchoring.EndsWith

    @Transient
    public val Contains: Anchoring.Contains = Anchoring.Contains
}
