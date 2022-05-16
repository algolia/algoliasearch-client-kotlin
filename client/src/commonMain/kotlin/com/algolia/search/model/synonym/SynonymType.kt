package com.algolia.search.model.synonym

import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(SynonymType.Companion::class)
public sealed class SynonymType(override val raw: String) : Raw<String> {

    public enum class Typo {
        One,
        Two
    }

    /**
     * Matches [Synonym.OneWay].
     */
    public object OneWay : SynonymType(Key.OneWaySynonym)

    /**
     * Matches [Synonym.MultiWay].
     */
    public object MultiWay : SynonymType(Key.Synonym)

    /**
     * Matches [Synonym.AlternativeCorrections].
     */
    public data class AlternativeCorrections(val typo: Typo) : SynonymType(
        when (typo) {
            Typo.One -> Key.AlternativeCorrection1
            Typo.Two -> Key.AlternativeCorrection2
        }
    )

    /**
     * Matches [Synonym.Placeholder]
     */
    public object Placeholder : SynonymType(Key.Placeholder)

    public data class Other(override val raw: String) : SynonymType(raw)

    public companion object : KSerializer<SynonymType> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: SynonymType) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): SynonymType {
            return when (val string = serializer.deserialize(decoder)) {
                Key.OneWaySynonym -> OneWay
                Key.Synonym -> MultiWay
                Key.AlternativeCorrection1 -> AlternativeCorrections(Typo.One)
                Key.AlternativeCorrection2 -> AlternativeCorrections(Typo.Two)
                Key.Placeholder -> Placeholder
                else -> Other(string)
            }
        }
    }
}
