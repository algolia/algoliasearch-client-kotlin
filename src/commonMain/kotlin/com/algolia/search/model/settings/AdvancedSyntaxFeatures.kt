package com.algolia.search.model.settings

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyExactPhrase
import com.algolia.search.serialize.KeyExcludeWords
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(AdvancedSyntaxFeatures.Companion::class)
public sealed class AdvancedSyntaxFeatures(override val raw: String) : Raw<String> {

    public object ExactPhrase : AdvancedSyntaxFeatures(KeyExactPhrase)

    public object ExcludeWords : AdvancedSyntaxFeatures(KeyExcludeWords)

    public data class Other(override val raw: String) : AdvancedSyntaxFeatures(raw)

    @Serializer(AdvancedSyntaxFeatures::class)
    internal companion object : KSerializer<AdvancedSyntaxFeatures> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: AdvancedSyntaxFeatures) {
            return serializer.serialize(encoder, obj.raw)
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