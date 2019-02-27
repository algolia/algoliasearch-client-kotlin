package com.algolia.search.model

import com.algolia.search.helper.dateISO8601
import com.algolia.search.helper.dateISO8601Millis
import com.algolia.search.serialize.KSerializerClientDate
import kotlinx.serialization.Serializable
import java.util.*


@Serializable(KSerializerClientDate::class)
public actual data class ClientDate actual constructor(override val raw: String) : Raw<String> {

    actual constructor(timestamp: Long) : this(dateISO8601.format(Date(timestamp)))

    val date: Date = when {
        raw.length == 20 -> dateISO8601.parse(raw)
        raw.length == 24 -> dateISO8601Millis.parse(raw)
        else -> Date()
    }
}