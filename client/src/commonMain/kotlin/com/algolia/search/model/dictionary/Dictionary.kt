package com.algolia.search.model.dictionary

import com.algolia.search.helper.internal.encodeUTF8
import com.algolia.search.model.dictionary.DictionaryEntry.Compound
import com.algolia.search.model.dictionary.DictionaryEntry.Plural
import com.algolia.search.model.dictionary.DictionaryEntry.Stopword
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.KeyCompounds
import com.algolia.search.serialize.KeyPlurals
import com.algolia.search.serialize.KeyStopwords
import com.algolia.search.serialize.internal.Route
import kotlinx.serialization.Serializable

/**
 * Represents a linguistic resources provided by Algolia.
 */
@Serializable
public sealed class Dictionary<T : DictionaryEntry>(
    override val raw: String,
) : Raw<String> {

    @Serializable
    public object Plurals : Dictionary<Plural>(KeyPlurals)

    @Serializable
    public object Stopwords : Dictionary<Stopword>(KeyStopwords)

    @Serializable
    public object Compounds : Dictionary<Compound>(KeyCompounds)

    private fun encode(): String {
        return raw.encodeUTF8()
    }

    internal fun toPath(suffix: String? = null): String {
        return "${Route.Dictionaries}/${encode()}" + (suffix ?: "")
    }

    override fun toString(): String {
        return raw
    }
}
