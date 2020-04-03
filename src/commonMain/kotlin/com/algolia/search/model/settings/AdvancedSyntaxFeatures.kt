package com.algolia.search.model.settings

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyExactPhrase
import com.algolia.search.serialize.KeyExcludeWords
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer

@Serializable(AdvancedSyntaxFeatures.Companion::class)
public sealed class AdvancedSyntaxFeatures(override val raw: String) : Raw<String> {

    public object ExactPhrase : AdvancedSyntaxFeatures(KeyExactPhrase)

    public object ExcludeWords : AdvancedSyntaxFeatures(KeyExcludeWords)

    public data class Other(override val raw: String) : AdvancedSyntaxFeatures(raw)

    @Serializer(AdvancedSyntaxFeatures::class)
    companion object : KSerializer<AdvancedSyntaxFeatures> {

        private val serializer = String.serializer()

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: AdvancedSyntaxFeatures) {
            return serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): AdvancedSyntaxFeatures {
            return when (val string = serializer.deserialize(decoder)) {
                KeyExactPhrase -> ExactPhrase
                KeyExcludeWords -> ExcludeWords
                else -> Other(string)
            }
        }
    }
}
