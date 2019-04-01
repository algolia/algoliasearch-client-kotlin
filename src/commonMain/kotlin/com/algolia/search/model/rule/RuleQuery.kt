package com.algolia.search.model.rule

import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Suppress("PropertyName")
@Serializable
public data class RuleQuery(
    @SerialName(KeyQuery) var query: String? = null,
    @SerialName(KeyAnchoring) var anchoring: Anchoring? = null,
    @SerialName(KeyContext) var context: String? = null,
    @SerialName(KeyPage) var page: Int? = null,
    @SerialName(KeyHitsPerPage) var hitsPerPage: Int? = null,
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