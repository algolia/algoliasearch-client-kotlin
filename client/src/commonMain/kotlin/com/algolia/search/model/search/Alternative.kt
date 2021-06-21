package com.algolia.search.model.search

import com.algolia.search.serialize.KeyLength
import com.algolia.search.serialize.KeyOffset
import com.algolia.search.serialize.KeyTypes
import com.algolia.search.serialize.KeyTypos
import com.algolia.search.serialize.KeyWords
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Alternative(
    @SerialName(KeyTypes) val types: List<AlternativeType>,
    @SerialName(KeyWords) val words: List<String>,
    @SerialName(KeyTypos) val typos: Int,
    @SerialName(KeyOffset) val offset: Int,
    @SerialName(KeyLength) val length: Int
)
