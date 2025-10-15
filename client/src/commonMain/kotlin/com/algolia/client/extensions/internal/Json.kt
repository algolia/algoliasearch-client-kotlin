package com.algolia.client.extensions.internal

import kotlinx.serialization.SerializationException
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

/**
 * Casts the [Decoder] instance to a [JsonDecoder] if possible, otherwise throws a
 * [SerializationException].
 */
internal fun Decoder.asJsonDecoder() =
  this as? JsonDecoder
    ?: throw SerializationException("This class can be decoded only by Json format")

/** Decodes the current JSON element as a [JsonObject] using the [JsonDecoder] instance. */
internal fun JsonDecoder.decodeJsonObject() =
  decodeJsonElement() as? JsonObject ?: throw SerializationException("Expected JsonObject")

/**
 * Casts the [Encoder] instance to a [JsonEncoder] if possible, otherwise throws a
 * [SerializationException].
 */
internal fun Encoder.asJsonEncoder() =
  this as? JsonEncoder
    ?: throw SerializationException("This class can be encoded only by Json format")

/** Returns true if [JsonElement] is an integer, false otherwise. */
internal val JsonElement.isInt: Boolean
  get() = this is JsonPrimitive && intOrNull != null

/** Returns true if [JsonElement] is a boolean, false otherwise. */
internal val JsonElement.isBoolean: Boolean
  get() = this is JsonPrimitive && booleanOrNull != null

/** Returns true if [JsonElement] is a float, false otherwise */
internal val JsonElement.isFloat: Boolean
  get() = this is JsonPrimitive && floatOrNull != null

/** Returns true if [JsonElement] is a double, false otherwise */
internal val JsonElement.isDouble: Boolean
  get() = this is JsonPrimitive && doubleOrNull != null

/** Returns true if [JsonElement] is a string, false otherwise. */
internal val JsonElement.isString: Boolean
  get() = this is JsonPrimitive && isString

/** Returns true if [JsonElement] is a [JsonArray] of primitives, false otherwise. */
internal val JsonElement.isJsonArrayOfPrimitives: Boolean
  get() = this is JsonArray && (isEmpty() || first() is JsonPrimitive)

/** Returns true if [JsonElement] is a [JsonArray] of objects, false otherwise. */
internal val JsonElement.isJsonArrayOfObjects: Boolean
  get() = this is JsonArray && (isEmpty() || first() is JsonObject)
