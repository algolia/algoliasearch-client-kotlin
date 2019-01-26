package com.algolia.search.saas.model

import com.algolia.search.saas.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(SynonymType.Companion::class)
sealed class SynonymType(override val raw: String) : Raw<String> {

    enum class Typo {
        One,
        Two
    }

    object OneWay : SynonymType(KeyOneWaySynonym)

    object MultiWay : SynonymType(KeySynonym)

    data class AlternativeCorrections(val typo: Typo) : SynonymType(
        when (typo) {
            Typo.One -> KeyAlternativeCorrection1
            Typo.Two -> KeyAlternativeCorrection2
        }
    )

    object Placeholder : SynonymType(KeyPlaceholder)

    data class Other(override val raw: String) : SynonymType(raw)

    companion object : KSerializer<SynonymType> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: SynonymType) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): SynonymType {
            val string = serializer.deserialize(decoder)

            return when (string) {
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