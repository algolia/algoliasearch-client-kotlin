package com.algolia.search.saas.data

import kotlinx.serialization.*
import kotlinx.serialization.internal.HashMapSerializer


@Serializable(DecompoundedAttributes.Companion::class)
data class DecompoundedAttributes internal constructor(
    val map: Map<QueryLanguage, List<Attribute>>
) {

    private constructor(
        language: QueryLanguage,
        attributes: List<Attribute>
    ) : this(mapOf(language to attributes.toList()))

    constructor(language: QueryLanguage.Finnish, vararg attributes: Attribute) : this(language, attributes.toList())
    constructor(language: QueryLanguage.German, vararg attributes: Attribute) : this(language, attributes.toList())
    constructor(language: QueryLanguage.Dutch, vararg attributes: Attribute) : this(language, attributes.toList())

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