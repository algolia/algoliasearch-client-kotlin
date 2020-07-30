package com.algolia.search.model.indexing

import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.serialize.KeyAdd
import com.algolia.search.serialize.KeyAddUnique
import com.algolia.search.serialize.KeyDecrement
import com.algolia.search.serialize.KeyIncrement
import com.algolia.search.serialize.KeyIncrementFrom
import com.algolia.search.serialize.KeyIncrementSet
import com.algolia.search.serialize.KeyRemove
import com.algolia.search.serialize.KeyValue
import com.algolia.search.serialize.Key_Operation
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import com.algolia.search.serialize.jsonPrimitiveOrNull
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.put

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

        public constructor(attribute: Attribute, value: String) : this(attribute, JsonPrimitive(value))

        public constructor(attribute: Attribute, value: Boolean) : this(attribute, JsonPrimitive(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonPrimitive(value))

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

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonPrimitive(value))
    }

    /**
     * Increment a numeric integer attribute only if the provided value matches the current value, and otherwise ignore
     * the whole object update. For example, if you pass an `IncrementFrom` value of 2 for the `version` attribute, but
     * the current value of the attribute is 1, the engine ignores the update. If the object doesn't exist, the engine
     * only creates it if you pass an `IncrementFrom` value of 0.
     */
    public data class IncrementFrom internal constructor(
        override val attribute: Attribute,
        override val value: JsonElement
    ) : Partial() {

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonPrimitive(value))
    }

    /**
     * Increment a numeric integer attribute only if the provided value is greater than the current value, and otherwise
     * ignore the whole object update. For example, if you pass an `IncrementSet` value of 2 for the `version`
     * attribute, and the current value of the attribute is 1, the engine updates the object. If the object doesn't
     * exist yet, the engine only creates it if you pass an `IncrementSet` value that's greater than 0.
     */
    public data class IncrementSet internal constructor(
        override val attribute: Attribute,
        override val value: JsonElement
    ) : Partial() {

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonPrimitive(value))
    }

    /**
     * Decrement a numeric based value for an attribute.
     */
    public data class Decrement internal constructor(
        override val attribute: Attribute,
        override val value: JsonElement
    ) : Partial() {

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonPrimitive(value))
    }

    /**
     * Append a number or string element to an array-based value for an attribute.
     */
    public data class Add internal constructor(
        override val attribute: Attribute,
        override val value: JsonElement
    ) : Partial() {

        public constructor(attribute: Attribute, value: String) : this(attribute, JsonPrimitive(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonPrimitive(value))
    }

    /**
     * Remove all matching number or string elements from an array-based value for an attribute
     */
    public data class Remove internal constructor(
        override val attribute: Attribute,
        override val value: JsonElement
    ) : Partial() {

        public constructor(attribute: Attribute, value: String) : this(attribute, JsonPrimitive(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonPrimitive(value))
    }

    /**
     * Add a number or string element to an array-based value for an attribute only if it’s not already present
     */
    public data class AddUnique internal constructor(
        override val attribute: Attribute,
        override val value: JsonElement
    ) : Partial() {

        public constructor(attribute: Attribute, value: String) : this(attribute, JsonPrimitive(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, JsonPrimitive(value))
    }

    @Serializer(Partial::class)
    public companion object : KSerializer<Partial> {

        override fun serialize(encoder: Encoder, value: Partial) {
            val key = when (value) {
                is Update -> null
                is Increment -> KeyIncrement
                is IncrementFrom -> KeyIncrementFrom
                is IncrementSet -> KeyIncrementSet
                is Decrement -> KeyDecrement
                is Add -> KeyAdd
                is Remove -> KeyRemove
                is AddUnique -> KeyAddUnique
            }
            val json = buildJsonObject {
                put(value.attribute.raw, buildJsonObject {
                    key?.let { put(Key_Operation, key) }
                    put(KeyValue, value.value)
                })
            }
            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): Partial {
            val element = decoder.asJsonInput().jsonObject
            val key = element.keys.first()
            val attribute = key.toAttribute()
            val operation = element.getValue(key).jsonObject[Key_Operation]?.jsonPrimitiveOrNull?.content
            val jsonElement = element.getValue(key).jsonObject.getValue(KeyValue)

            return when (operation) {
                null -> Update(attribute, jsonElement)
                KeyIncrement -> Increment(attribute, jsonElement)
                KeyIncrementFrom -> IncrementFrom(attribute, jsonElement)
                KeyIncrementSet -> IncrementSet(attribute, jsonElement)
                KeyDecrement -> Decrement(attribute, jsonElement)
                KeyAdd -> Add(attribute, jsonElement)
                KeyRemove -> Remove(attribute, jsonElement)
                KeyAddUnique -> AddUnique(attribute, jsonElement)
                else -> throw Exception("Unknown operation $operation")
            }
        }
    }
}
