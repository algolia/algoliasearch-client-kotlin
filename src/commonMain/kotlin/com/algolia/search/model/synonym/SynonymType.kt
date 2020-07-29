package com.algolia.search.model.synonym

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAlternativeCorrection1
import com.algolia.search.serialize.KeyAlternativeCorrection2
import com.algolia.search.serialize.KeyOneWaySynonym
import com.algolia.search.serialize.KeyPlaceholder
import com.algolia.search.serialize.KeySynonym
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(SynonymType.Companion::class)
public sealed class SynonymType(override val raw: String) : Raw<String> {

    public enum class Typo {
        One,
        Two
    }

    /**
     * Matches [Synonym.OneWay].
     */
    public object OneWay : SynonymType(KeyOneWaySynonym)

    /**
     * Matches [Synonym.MultiWay].
     */
    public object MultiWay : SynonymType(KeySynonym)

    /**
     * Matches [Synonym.AlternativeCorrections].
     */
    public data class AlternativeCorrections(val typo: Typo) : SynonymType(
        when (typo) {
            Typo.One -> KeyAlternativeCorrection1
            Typo.Two -> KeyAlternativeCorrection2
        }
    )

    /**
     * Matches [Synonym.Placeholder]
     */
    public object Placeholder : SynonymType(KeyPlaceholder)

    public data class Other(override val raw: String) : SynonymType(raw)

    public companion object : KSerializer<SynonymType> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: SynonymType) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): SynonymType {
            return when (val string = serializer.deserialize(decoder)) {
                KeyOneWaySynonym -> OneWay
                KeySynonym -> MultiWay
                KeyAlternativeCorrection1 -> AlternativeCorrections(Typo.One)
                KeyAlternativeCorrection2 -> AlternativeCorrections(Typo.Two)
                KeyPlaceholder -> Placeholder
                else -> Other(string)
            }
        }
    }
}
