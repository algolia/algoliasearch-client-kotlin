package com.algolia.search.model.settings

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.serialize.KeyOrdered
import com.algolia.search.serialize.KeyUnordered
import com.algolia.search.serialize.regexOrdered
import com.algolia.search.serialize.regexUnordered
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(SearchableAttribute.Companion::class)
public sealed class SearchableAttribute {

    public data class Default(val attributes: List<Attribute>) : SearchableAttribute() {

        public constructor(vararg attributes: Attribute) : this(attributes.toList())
    }

    public data class Ordered(val attribute: Attribute) : SearchableAttribute()

    public data class Unordered(val attribute: Attribute) : SearchableAttribute()

    @Serializer(SearchableAttribute::class)
    internal companion object : KSerializer<SearchableAttribute> {

        override fun serialize(encoder: Encoder, obj: SearchableAttribute) {
            val string = when (obj) {
                is Default -> obj.attributes.joinToString { it.raw }
                is Ordered -> "$KeyOrdered(${obj.attribute.raw})"
                is Unordered -> "$KeyUnordered(${obj.attribute.raw})"
            }
            StringSerializer.serialize(encoder, string)
        }

        override fun deserialize(decoder: Decoder): SearchableAttribute {
            val string = StringSerializer.deserialize(decoder)
            val findOrdered = regexOrdered.find(string)
            val findUnordered = regexUnordered.find(string)

            return when {
                findOrdered != null -> Ordered(findOrdered.groupValues[1].toAttribute())
                findUnordered != null -> Unordered(findUnordered.groupValues[1].toAttribute())
                else -> Default(string.split(", ").map { it.toAttribute() })
            }
        }
    }
}