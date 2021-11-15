package com.algolia.search.model

import com.algolia.search.helper.internal.DateISO8601
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.KSerializerClientDate
import kotlinx.serialization.Serializable

/**
 * JVM implementation converting a [String] or a [Long] into a [Date] format. Relies on ISO8601.
 */
@Serializable(KSerializerClientDate::class)
public actual data class ClientDate internal actual constructor(override val raw: String) : Raw<String> {

    internal actual constructor(timestamp: Long) : this(DateISO8601.format(timestamp, false))
}
