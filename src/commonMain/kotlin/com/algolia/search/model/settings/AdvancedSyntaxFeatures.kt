package com.algolia.search.model.settings

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyExactPhrase
import com.algolia.search.serialize.KeyExcludeWords
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringSerializer

@Serializable(AdvancedSyntaxFeatures.Companion::class)
sealed class AdvancedSyntaxFeatures(override val raw: String) : Raw<String> {

    object ExactPhrase : AdvancedSyntaxFeatures(KeyExactPhrase)

    object ExcludeWords : AdvancedSyntaxFeatures(KeyExcludeWords)

    data class Other(override val raw: String) : AdvancedSyntaxFeatures(raw)

    @Serializer(AdvancedSyntaxFeatures::class)
    companion object : KSerializer<AdvancedSyntaxFeatures> {

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
