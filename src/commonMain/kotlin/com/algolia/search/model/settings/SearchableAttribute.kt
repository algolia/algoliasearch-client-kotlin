package com.algolia.search.model.settings

import com.algolia.search.model.Attribute
import com.algolia.search.serialize.KeyOrdered
import com.algolia.search.serialize.KeyUnordered
import com.algolia.search.serialize.regexOrdered
import com.algolia.search.serialize.regexUnordered
import com.algolia.search.helper.toAttribute
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(SearchableAttribute.Companion::class)
sealed class SearchableAttribute(open val attribute: Attribute) {

    data class Default(override val attribute: Attribute) : SearchableAttribute(attribute)

    data class Ordered(override val attribute: Attribute) : SearchableAttribute(attribute)

    data class Unordered(override val attribute: Attribute) : SearchableAttribute(attribute)

    @Serializer(SearchableAttribute::class)
    companion object : KSerializer<SearchableAttribute> {

        override fun serialize(encoder: Encoder, obj: SearchableAttribute) {
            val string = when (obj) {
                is Default -> obj.attribute.raw
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
                else -> Default(string.toAttribute())
            }
        }
    }
}