package com.algolia.search.configuration

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyEU
import com.algolia.search.serialize.KeyUS


/**
 * Available regions, used in [ConfigurationRecommendation].
 */
public sealed class Region(override val raw: String) : Raw<String> {

    public object EU : Region(KeyEU)

    public object US : Region(KeyUS)

    public data class Other(override val raw: String) : Region(raw)

    override fun toString(): String {
        return raw
    }
}