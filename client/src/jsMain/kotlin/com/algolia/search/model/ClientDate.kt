package com.algolia.search.model

import com.algolia.search.helper.internal.DateISO8601
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.KSerializerClientDate
import kotlinx.serialization.Serializable

/**
 * Convert a [String] or [Long] date format into a platform specific Date object.
 */
@Serializable(KSerializerClientDate::class)
public actual class ClientDate internal actual constructor(override val raw: String) : Raw<String> {
    internal actual constructor(timestamp: Long) : this(DateISO8601.format(timestamp, false))
}
