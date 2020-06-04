package com.algolia.search.configuration

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyDE
import com.algolia.search.serialize.KeyEU
import com.algolia.search.serialize.KeyUS

/**
 * Region configuration, used in some [Configuration] implementations.
 */
public sealed class Region {

    /**
     * Available analytics' regions, used in [ConfigurationAnalytics].
     */
    public sealed class Analytics(override val raw: String) : Raw<String> {

        public object EU : Analytics(KeyDE)
        public object US : Analytics(KeyUS)
        public class Other(override val raw: String) : Analytics(raw)

        override fun toString(): String = raw
    }

    /**
     * Available regions, used in [ConfigurationRecommendation].
     */
    public sealed class Recommendation(override val raw: String) : Raw<String> {

        public object EU : Recommendation(KeyEU)
        public object US : Recommendation(KeyUS)
        public class Other(override val raw: String) : Recommendation(raw)

        override fun toString(): String = raw
    }
}
