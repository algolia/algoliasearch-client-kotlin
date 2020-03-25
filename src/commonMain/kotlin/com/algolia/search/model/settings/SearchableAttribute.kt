package com.algolia.search.model.settings

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.serialize.KeyUnordered
import com.algolia.search.serialize.regexUnordered
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringSerializer

@Serializable(SearchableAttribute.Companion::class)
sealed class SearchableAttribute {

    /**
     * Putting several [attributes] in the same [Default] object will assign the same priority to each attribute.
     */
    data class Default(val attributes: List<Attribute>) : SearchableAttribute() {

        constructor(vararg attributes: Attribute) : this(attributes.toList())
    }

    /**
     * By default, searchable attributes are set as ordered: matches at the beginning of an attribute are more important
     * than in the middle, and matches in the middle are more important than towards the end.
     * Setting them as [Unordered] cancels out this behavior.
     */
    data class Unordered(val attribute: Attribute) : SearchableAttribute()

    @Serializer(SearchableAttribute::class)
    companion object : KSerializer<SearchableAttribute> {

        override fun serialize(encoder: Encoder, obj: SearchableAttribute) {
            val string = when (obj) {
                is Default -> obj.attributes.joinToString { it.raw }
                is Unordered -> "$KeyUnordered(${obj.attribute.raw})"
            }
            StringSerializer.serialize(encoder, string)
        }

        override fun deserialize(decoder: Decoder): SearchableAttribute {
            val string = StringSerializer.deserialize(decoder)
            val findUnordered = regexUnordered.find(string)

            return when {
                findUnordered != null -> Unordered(findUnordered.groupValues[1].toAttribute())
                else -> Default(string.split(", ").map { it.toAttribute() })
            }
        }
    }
}
