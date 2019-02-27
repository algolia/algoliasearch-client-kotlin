package com.algolia.search.model.settings

import com.algolia.search.model.Attribute
import com.algolia.search.serialize.KeyFilterOnly
import com.algolia.search.serialize.KeySearchable
import com.algolia.search.serialize.regexFilterOnly
import com.algolia.search.serialize.regexSearchable
import com.algolia.search.helper.toAttribute
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(AttributeForFaceting.Companion::class)
sealed class AttributeForFaceting(open val attribute: Attribute) {

    data class Default(override val attribute: Attribute) : AttributeForFaceting(attribute)

    data class FilterOnly(override val attribute: Attribute) : AttributeForFaceting(attribute)

    data class Searchable(override val attribute: Attribute) : AttributeForFaceting(attribute)

    @Serializer(AttributeForFaceting::class)
    companion object : KSerializer<AttributeForFaceting> {

        override fun serialize(encoder: Encoder, obj: AttributeForFaceting) {
            val string = when (obj) {
                is Default -> obj.attribute.raw
                is FilterOnly -> "$KeyFilterOnly(${obj.attribute.raw})"
                is Searchable -> "$KeySearchable(${obj.attribute.raw})"
            }
            StringSerializer.serialize(encoder, string)
        }

        override fun deserialize(decoder: Decoder): AttributeForFaceting {
            val string = StringSerializer.deserialize(decoder)
            val findFilterOnly = regexFilterOnly.find(string)
            val findSearchable = regexSearchable.find(string)

            return when {
                findFilterOnly != null -> FilterOnly(findFilterOnly.groupValues[1].toAttribute())
                findSearchable != null -> Searchable(findSearchable.groupValues[1].toAttribute())
                else -> Default(string.toAttribute())
            }
        }
    }
}