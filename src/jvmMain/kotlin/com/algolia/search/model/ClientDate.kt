package com.algolia.search.model

import com.algolia.search.helper.DateISO8601
import com.algolia.search.serialize.KSerializerClientDate
import kotlinx.serialization.Serializable
import java.util.*


@Serializable(KSerializerClientDate::class)
actual data class ClientDate actual constructor(override val raw: String) : Raw<String> {

    actual constructor(timestamp: Long) : this(DateISO8601.format(timestamp))

    val date: Date = when {
        raw.length == 20 -> DateISO8601.dateISO8601.parse(raw)
        raw.length == 24 -> DateISO8601.dateISO8601Millis.parse(raw)
        else -> Date()
    }
}