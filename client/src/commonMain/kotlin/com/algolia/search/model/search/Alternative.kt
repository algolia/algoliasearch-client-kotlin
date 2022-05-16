package com.algolia.search.model.search

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Alternative(
    @SerialName(Key.Types) val types: List<AlternativeType>,
    @SerialName(Key.Words) val words: List<String>,
    @SerialName(Key.Typos) val typos: Int,
    @SerialName(Key.Offset) val offset: Int,
    @SerialName(Key.Length) val length: Int
)
