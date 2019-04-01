package com.algolia.search.model.settings

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.QueryLanguage
import kotlinx.serialization.*
import kotlinx.serialization.internal.HashMapSerializer


@Serializable(DecompoundedAttributes.Companion::class)
public data class DecompoundedAttributes internal constructor(
    val map: Map<QueryLanguage, List<Attribute>>
) {

    internal constructor(
        language: QueryLanguage,
        attributes: List<Attribute>
    ) : this(mapOf(language to attributes.toList()))

    public constructor(
        language: QueryLanguage.German,
        vararg attributes: Attribute
    ) : this(language, attributes.toList())

    public constructor(
        language: QueryLanguage.Finnish,
        vararg attributes: Attribute
    ) : this(language, attributes.toList())

    public constructor(
        language: QueryLanguage.Dutch,
        vararg attributes: Attribute
    ) : this(language, attributes.toList())

    internal companion object : KSerializer<DecompoundedAttributes> {

        private val serializer = HashMapSerializer(QueryLanguage, Attribute.list)

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: DecompoundedAttributes) {
            serializer.serialize(encoder, obj.map)
        }

        override fun deserialize(decoder: Decoder): DecompoundedAttributes {
            return DecompoundedAttributes(serializer.deserialize(decoder))
        }
    }
}