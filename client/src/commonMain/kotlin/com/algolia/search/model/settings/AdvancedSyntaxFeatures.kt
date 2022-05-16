package com.algolia.search.model.settings

import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(AdvancedSyntaxFeatures.Companion::class)
public sealed class AdvancedSyntaxFeatures(override val raw: String) : Raw<String> {

    public object ExactPhrase : AdvancedSyntaxFeatures(Key.ExactPhrase)

    public object ExcludeWords : AdvancedSyntaxFeatures(Key.ExcludeWords)

    public data class Other(override val raw: String) : AdvancedSyntaxFeatures(raw)

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(AdvancedSyntaxFeatures::class)
    public companion object : KSerializer<AdvancedSyntaxFeatures> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: AdvancedSyntaxFeatures) {
            return serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): AdvancedSyntaxFeatures {
            return when (val string = serializer.deserialize(decoder)) {
                Key.ExactPhrase -> ExactPhrase
                Key.ExcludeWords -> ExcludeWords
                else -> Other(string)
            }
        }
    }
}
