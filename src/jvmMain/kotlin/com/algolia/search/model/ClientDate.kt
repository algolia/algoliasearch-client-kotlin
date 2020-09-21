package com.algolia.search.model

import com.algolia.search.helper.internal.DateISO8601
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.KSerializerClientDate
import kotlinx.serialization.Serializable
import java.util.Date

/**
 * JVM implementation converting a [String] or a [Long] into a [Date] format. Relies on ISO8601.
 */
@Serializable(KSerializerClientDate::class)
public actual data class ClientDate internal actual constructor(override val raw: String) : Raw<String> {

    internal actual constructor(timestamp: Long) : this(DateISO8601.format(timestamp, false))

    /**
     * In the eventuality of the Date format being wrong, we create an empty [Date] object instead of throwing an exception.
     */
    val date: Date = when (raw.length) {
        20 -> DateISO8601.dateISO8601.parse(raw)
        24 -> DateISO8601.dateISO8601Millis.parse(raw)
        else -> Date()
    }
}
