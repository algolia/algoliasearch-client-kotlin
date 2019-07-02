package com.algolia.search.model

import com.algolia.search.helper.DateISO8601
import com.algolia.search.serialize.KSerializerClientDate
import kotlinx.serialization.Serializable
import java.util.*


/**
 * JVM implementation converting a [String] or a [Long] into a [Date] format. Relies on ISO8601.
 */
@Serializable(KSerializerClientDate::class)
public actual data class ClientDate internal actual constructor(override val raw: String) : Raw<String> {

    internal actual constructor(timestamp: Long) : this(DateISO8601.format(timestamp))

    /**
     * In the eventuality of the Date format is wrong, we create an empty [Date] object instead of throwing an exception.
     */
    val date: Date = when {
        raw.length == 20 -> DateISO8601.dateISO8601.parse(raw)
        raw.length == 24 -> DateISO8601.dateISO8601Millis.parse(raw)
        else -> Date()
    }
}