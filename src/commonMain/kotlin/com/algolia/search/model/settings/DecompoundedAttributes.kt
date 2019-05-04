package com.algolia.search.model.settings

import com.algolia.search.model.Attribute
import com.algolia.search.model.search.QueryLanguage
import kotlinx.serialization.*
import kotlinx.serialization.internal.HashMapSerializer


/**
 * Specify on which attributes in your index Algolia should apply word-splitting (“decompounding”).
 * A compound word refers to a word that is formed by combining smaller words without spacing.
 * They are called noun phrases, or nominal groups, and they are particularly present in German.
 * An example is Baumhaus, which is a contraction of Baum and Haus.
 * The goal of decompounding, regarding the previous example, is to index both Baum and Haus separately,
 * nstead of as a single word.
 */
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

    companion object : KSerializer<DecompoundedAttributes> {

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