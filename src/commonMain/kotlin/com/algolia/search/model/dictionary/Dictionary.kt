package com.algolia.search.model.dictionary

import com.algolia.search.helper.internal.StringUTF8
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.KeyCompounds
import com.algolia.search.serialize.KeyPlurals
import com.algolia.search.serialize.KeyStopwords
import com.algolia.search.serialize.RouteDictionaries
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
public sealed class Dictionary(
    override val raw: String,
) : Raw<String> {

    public object Plurals : Dictionary(KeyPlurals)
    public object Stopwords : Dictionary(KeyStopwords)
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

    public companion object : KSerializer<Dictionary> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: Dictionary) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): Dictionary {
            return when (val raw = serializer.deserialize(decoder)) {
                KeyPlurals -> Plurals
                KeyStopwords -> Stopwords
                KeyCompounds -> Compounds
                else -> throw UnsupportedOperationException("Unknown dictionary: $raw")
            }
        }
    }
}
