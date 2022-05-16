package com.algolia.search.configuration

import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key

/**
 * Region configuration, used in some [Configuration] implementations.
 */
public sealed class Region {

    /**
     * Available analytics' regions, used in [ConfigurationAnalytics].
     */
    public sealed class Analytics(override val raw: String) : Raw<String> {

        public object EU : Analytics(Key.DE)
        public object US : Analytics(Key.US)
        public class Other(override val raw: String) : Analytics(raw)

        override fun toString(): String = raw
    }

    /**
     * Available regions, used in [ConfigurationPersonalization].
     */
    public sealed class Personalization(override val raw: String) : Raw<String> {

        public object EU : Personalization(Key.EU)
        public object US : Personalization(Key.US)
        public class Other(override val raw: String) : Personalization(raw)

        override fun toString(): String = raw
    }
}
