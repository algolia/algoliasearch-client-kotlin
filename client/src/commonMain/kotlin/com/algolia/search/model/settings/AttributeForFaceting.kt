package com.algolia.search.model.settings

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.regexFilterOnly
import com.algolia.search.serialize.internal.regexSearchable
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(AttributeForFaceting.Companion::class)
public sealed class AttributeForFaceting {

    public abstract val attribute: Attribute

    public data class Default(override val attribute: Attribute) : AttributeForFaceting()

    /**
     * Defines an [Attribute] as filterable only and not facetable.
     * If you only need the filtering feature, you can take advantage of filterOnly which will reduce the index size
     * and improve the speed of the search.
     * You cannot define an attribute as both [FilterOnly] and [Searchable].
     */
    public data class FilterOnly(override val attribute: Attribute) : AttributeForFaceting()

    /**
     * Defines an attribute as searchable.
     * If you want to search for values of a given facet (using the Search for facet values method
     * you need to specify [Searchable].
     * You cannot define an attribute as both [Searchable] and [FilterOnly].
     */
    public data class Searchable(override val attribute: Attribute) : AttributeForFaceting()

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(AttributeForFaceting::class)
    public companion object : KSerializer<AttributeForFaceting> {

        override fun serialize(encoder: Encoder, value: AttributeForFaceting) {
            val string = when (value) {
                is Default -> value.attribute.raw
                is FilterOnly -> "${Key.FilterOnly}(${value.attribute.raw})"
                is Searchable -> "${Key.Searchable}(${value.attribute.raw})"
            }
            String.serializer().serialize(encoder, string)
        }

        override fun deserialize(decoder: Decoder): AttributeForFaceting {
            val string = String.serializer().deserialize(decoder)
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
