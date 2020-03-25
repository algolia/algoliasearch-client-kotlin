package com.algolia.search.model.rule

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.model.Raw
import com.algolia.search.serialize.regexFacet
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer

/**
 * An empty [Pattern] is only allowed when the [Anchoring] is set to [Anchoring.Is].
 * Special characters ({, }, : and \) must be escaped by preceding them with a backslash (\) if they are to be
 * treated as literals.
 */
@Serializable(Pattern.Companion::class)
sealed class Pattern(override val raw: String) : Raw<String> {

    data class Facet(val attribute: Attribute) : Pattern("{facet:$attribute}")

    data class Literal(override val raw: String) : Pattern(raw)

    companion object : KSerializer<Pattern> {

        private val serializer = StringSerializer

        override val descriptor = StringSerializer.descriptor

        override fun serialize(encoder: Encoder, obj: Pattern) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Pattern {
            val string = serializer.deserialize(decoder)
            val match = regexFacet.find(string)

            return when {
                match != null -> Facet(match.groupValues[1].toAttribute())
                else -> Literal(string)
            }
        }
    }
}
