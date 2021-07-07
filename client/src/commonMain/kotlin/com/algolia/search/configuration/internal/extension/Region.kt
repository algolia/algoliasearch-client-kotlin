package com.algolia.search.configuration.internal.extension

import com.algolia.search.configuration.Region

/** Converts legacy recommendation region to personalization region **/
@Suppress("DEPRECATION")
internal fun Region.Recommendation.toPersonalization(): Region.Personalization {
    return when (this) {
        is Region.Recommendation.EU -> Region.Personalization.EU
        is Region.Recommendation.US -> Region.Personalization.US
        is Region.Recommendation.Other -> Region.Personalization.Other(raw)
    }
}
