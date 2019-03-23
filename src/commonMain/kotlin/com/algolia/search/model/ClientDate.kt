package com.algolia.search.model

import com.algolia.search.serialize.KSerializerClientDate
import kotlinx.serialization.Serializable


@Serializable(KSerializerClientDate::class)
expect class ClientDate(raw: String) : Raw<String> {

    constructor(timestamp: Long)
}