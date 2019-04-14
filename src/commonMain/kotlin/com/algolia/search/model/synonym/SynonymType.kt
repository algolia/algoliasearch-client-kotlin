package com.algolia.search.model.synonym

import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(SynonymType.Companion::class)
public sealed class SynonymType(override val raw: String) : Raw<String> {

    public enum class Typo {
        One,
        Two
    }

    public object OneWay : SynonymType(KeyOneWaySynonym)

    public object MultiWay : SynonymType(KeySynonym)

    public data class AlternativeCorrections(val typo: Typo) : SynonymType(
        when (typo) {
            Typo.One -> KeyAlternativeCorrection1
            Typo.Two -> KeyAlternativeCorrection2
        }
    )

    public object Placeholder : SynonymType(KeyPlaceholder)

    public data class Other(override val raw: String) : SynonymType(raw)

    companion object : KSerializer<SynonymType> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: SynonymType) {
            serializer.serialize(encoder, obj.raw)
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