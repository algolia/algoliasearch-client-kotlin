package com.algolia.search.serialize

import com.algolia.search.model.dictionary.Dictionary
import com.algolia.search.model.dictionary.DictionaryEntry
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * DictionaryEntry's [KSerializer] implementation.
 */
public class KSerializerDictionaryEntry<T : Dictionary>(
    dictionarySerializer: KSerializer<T>,
) : KSerializer<DictionaryEntry<T>> {

    @Suppress("UNCHECKED_CAST")
    private val serializer = when (dictionarySerializer.descriptor) {
        Dictionary.Stopwords.serializer().descriptor -> DictionaryEntry.Stopword.serializer()
        Dictionary.Plurals.serializer().descriptor -> DictionaryEntry.Plural.serializer()
        Dictionary.Compounds.serializer().descriptor -> DictionaryEntry.Compound.serializer()
        else -> throw SerializationException("Unknown Dictionary descriptor: ${dictionarySerializer.descriptor}")
    } as KSerializer<DictionaryEntry<T>>

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun deserialize(decoder: Decoder): DictionaryEntry<T> {
        return decoder.decodeSerializableValue(serializer)
    }

    override fun serialize(encoder: Encoder, value: DictionaryEntry<T>) {
        return serializer.serialize(encoder, value)
    }
}
