package com.algolia.search.model.queryrule

import com.algolia.search.model.Attribute
import com.algolia.search.model.Raw
import com.algolia.search.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(Pattern.Companion::class)
sealed class Pattern(override val raw: String) : Raw<String> {

    data class Facet(val attribute: Attribute) : Pattern("{facet:$attribute}")

    data class Literal(override val raw: String) : Pattern(raw)

    companion object : KSerializer<Pattern> {

        private val serializer = StringSerializer

        override val descriptor = StringSerializer.descriptor

        override fun serialize(encoder: Encoder, obj: Pattern) {
            StringSerializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Pattern {
            val string = StringSerializer.deserialize(decoder)
            val regex = Regex("\\{facet:(.*)}")
            val match = regex.find(string)

            return when {
                match != null -> Facet(match.groupValues[1].toAttribute())
                else -> Literal(string)
            }
        }
    }
}