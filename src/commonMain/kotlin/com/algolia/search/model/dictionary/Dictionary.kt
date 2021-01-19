package com.algolia.search.model.dictionary

import com.algolia.search.helper.internal.StringUTF8
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.KeyCompounds
import com.algolia.search.serialize.KeyPlurals
import com.algolia.search.serialize.KeyStopwords
import com.algolia.search.serialize.RouteDictionaries
import kotlinx.serialization.Serializable

@Serializable
public sealed class Dictionary(
    override val raw: String,
) : Raw<String> {

    @Serializable
    public object Plurals : Dictionary(KeyPlurals)

    @Serializable
    public object Stopwords : Dictionary(KeyStopwords)

    @Serializable
    public object Compounds : Dictionary(KeyCompounds)

    private fun encode(): StringUTF8 {
        return StringUTF8.encode(raw)
    }

    internal fun toPath(suffix: String? = null): String {
        return "$RouteDictionaries/${encode().string}" + (suffix ?: "")
    }

    override fun toString(): String {
        return raw
    }
}
