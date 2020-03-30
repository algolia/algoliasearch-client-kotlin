package com.algolia.search.model.indexing

import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.serialize.KeyAdd
import com.algolia.search.serialize.KeyAddUnique
import com.algolia.search.serialize.KeyDecrement
import com.algolia.search.serialize.KeyIncrement
import com.algolia.search.serialize.KeyRemove
import com.algolia.search.serialize.KeyValue
import com.algolia.search.serialize.Key_Operation
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json

/**
 * An object used to define a partial update operation with the [EndpointIndexing.partialUpdateObject] method.
 */
public sealed class Partial {

    /**
     * The attribute on which the partial update will be applied.
     */
    public abstract val attribute: Attribute

    internal abstract val value: JsonElement

    /**
     * Add or update the value of an attribute.
     * Several convenience constructors are available for each value type.
     */
    public data class Update internal constructor(
        override val attribute: Attribute,
        override val value: JsonElement
    ) : Partial() {

        public constructor(attribute: Attribute, value: String) : this(attribute, JsonLiteral(value))

        public constructor(attribute: Attribute, value: Boolean) : this(attribute, JsonLiteral(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonLiteral(value))

        public constructor(attribute: Attribute, value: JsonArray) : this(attribute, value as JsonElement)

        public constructor(attribute: Attribute, value: JsonObject) : this(attribute, value as JsonElement)
    }

    /**
     * Increment a numeric based value for an attribute.
     */
    public data class Increment internal constructor(
        override val attribute: Attribute,
        override val value: JsonElement
    ) : Partial() {

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonLiteral(value))
    }

    /**
     * Decrement a numeric based value for an attribute.
     */
    public data class Decrement internal constructor(
        override val attribute: Attribute,
        override val value: JsonElement
    ) : Partial() {

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonLiteral(value))
    }

    /**
     * Append a number or string element to an array-based value for an attribute.
     */
    public data class Add internal constructor(
        override val attribute: Attribute,
        override val value: JsonElement
    ) : Partial() {

        public constructor(attribute: Attribute, value: String) : this(attribute, JsonLiteral(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonLiteral(value))
    }

    /**
     * Remove all matching number or string elements from an array-based value for an attribute
     */
    public data class Remove internal constructor(
        override val attribute: Attribute,
        override val value: JsonElement
    ) : Partial() {

        public constructor(attribute: Attribute, value: String) : this(attribute, JsonLiteral(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonLiteral(value))
    }

    /**
     * Add a number or string element to an array-based value for an attribute only if it’s not already present
     */
    public data class AddUnique internal constructor(
        override val attribute: Attribute,
        override val value: JsonElement
    ) : Partial() {

        public constructor(attribute: Attribute, value: String) : this(attribute, JsonLiteral(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonLiteral(value))
    }

    @Serializer(Partial::class)
    companion object : KSerializer<Partial> {

        override fun serialize(encoder: Encoder, obj: Partial) {
            val key = when (obj) {
                is Update -> null
                is Increment -> KeyIncrement
                is Decrement -> KeyDecrement
                is Add -> KeyAdd
                is Remove -> KeyRemove
                is AddUnique -> KeyAddUnique
            }
            val json = json {
                obj.attribute.raw to json {
                    key?.let { Key_Operation to key }
                    KeyValue to obj.value
                }
            }
            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): Partial {
            val element = decoder.asJsonInput().jsonObject
            val key = element.keys.first()
            val attribute = key.toAttribute()
            val operation = element.getObject(key).getPrimitiveOrNull(Key_Operation)?.content
            val jsonElement = element.getObject(key).getValue(KeyValue)

            return when (operation) {
                null -> Update(attribute, jsonElement)
                KeyIncrement -> Increment(attribute, jsonElement)
                KeyDecrement -> Decrement(attribute, jsonElement)
                KeyAdd -> Add(attribute, jsonElement)
                KeyRemove -> Remove(attribute, jsonElement)
                KeyAddUnique -> AddUnique(attribute, jsonElement)
                else -> throw Exception("Unknown operation $operation")
            }
        }
    }
}
