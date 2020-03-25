package com.algolia.search.model

import com.algolia.search.serialize.KSerializerClientDate
import kotlinx.serialization.Serializable

/**
 * Convert a [String] or [Long] date format into a platform specific Date object.
 */
@Serializable(KSerializerClientDate::class)
expect class ClientDate internal constructor(raw: String) : Raw<String> {

    internal constructor(timestamp: Long)
}
