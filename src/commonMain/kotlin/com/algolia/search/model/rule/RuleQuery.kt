package com.algolia.search.model.rule

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.jvm.JvmOverloads


@Suppress("PropertyName")
@DSLParameters
@Serializable
public data class RuleQuery @JvmOverloads constructor(
    /**
     * Engine default: ""
     * Full text query.
     */
    @SerialName(KeyQuery) var query: String? = null,
    /**
     * Engine default: null
     * When not null, restricts matches to rules with a specific [Anchoring]. Otherwise all [Anchoring] may match.
     */
    @SerialName(KeyAnchoring) var anchoring: Anchoring? = null,
    /**
     * Restricts matches to contextual rules with a specific context (exact match).
     */
    @SerialName(KeyContext) var context: String? = null,
    /**
     * Engine default: 0
     * Requested page.
     */
    @SerialName(KeyPage) var page: Int? = null,
    /**
     * Engine default: 20
     * Maximum number of hits in a page. Minimum is 1, maximum is 1000.
     */
    @SerialName(KeyHitsPerPage) var hitsPerPage: Int? = null,
    /**
     * Engine default: null
     * When specified, restricts matches to [Rule] with a specific [Rule.enabled].
     * When absent (default), all [Rule] are retrieved, regardless of their [Rule.enabled].
     */
    @SerialName(KeyEnabled) var enabled: Boolean? = null
) {

    @Transient
    public val Is = Anchoring.Is

    @Transient
    public val StartsWith = Anchoring.StartsWith

    @Transient
    public val EndsWith = Anchoring.EndsWith

    @Transient
    public val Contains = Anchoring.Contains
}