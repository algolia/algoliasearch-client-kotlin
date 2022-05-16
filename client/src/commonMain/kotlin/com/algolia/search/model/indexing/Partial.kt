package com.algolia.search.model.indexing

import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import com.algolia.search.serialize.internal.jsonPrimitiveOrNull
import kotlinx.serialization.ExperimentalSerializationApi
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

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(Partial::class)
    public companion object : KSerializer<Partial> {

        override fun serialize(encoder: Encoder, value: Partial) {
            val key = when (value) {
                is Update -> null
                is Increment -> Key.Increment
                is IncrementFrom -> Key.IncrementFrom
                is IncrementSet -> Key.IncrementSet
                is Decrement -> Key.Decrement
                is Add -> Key.Add
                is Remove -> Key.Remove
                is AddUnique -> Key.AddUnique
            }
            val json = buildJsonObject {
                put(
                    value.attribute.raw,
                    key?.let {
                        buildJsonObject {
                            put(Key._Operation, key)
                            put(Key.Value, value.value)
                        }
                    } ?: value.value
                )
            }
            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): Partial {
            val element = decoder.asJsonInput().jsonObject
            val key = element.keys.first()
            val attribute = key.toAttribute()
            val value = element.getValue(key)
            val hasOperation = (value is JsonObject && value.jsonObject.containsKey(Key._Operation))
            val operation = if (hasOperation) value.jsonObject[Key._Operation]?.jsonPrimitiveOrNull?.content else null
            val jsonElement = if (hasOperation) value.jsonObject.getValue((Key.Value)) else value

            return when (operation) {
                null -> Update(attribute, jsonElement)
                Key.Increment -> Increment(attribute, jsonElement)
                Key.IncrementFrom -> IncrementFrom(attribute, jsonElement)
                Key.IncrementSet -> IncrementSet(attribute, jsonElement)
                Key.Decrement -> Decrement(attribute, jsonElement)
                Key.Add -> Add(attribute, jsonElement)
                Key.Remove -> Remove(attribute, jsonElement)
                Key.AddUnique -> AddUnique(attribute, jsonElement)
                else -> throw Exception("Unknown operation $operation")
            }
        }
    }
}
