package data

import client.serialize.Serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import testDeserialize
import testDeserializeArray
import testDeserializeNull
import testSerialize
import testSerializeArray
import testSerializeNull


internal interface TestSerializer<T> {

    val serializer: Serializer<T>

    fun testSerialize(expected: JsonElement, actual: T) = testSerialize(expected, actual, serializer)
    fun testSerializeNull() = testSerializeNull(serializer)
    fun testSerializeArray(expected: JsonArray, actual: List<T>) = testSerializeArray(expected, actual, serializer)
    fun testDeserialize(expected: T, actual: JsonElement) = testDeserialize(expected, actual, serializer)
    fun testDeserializeNull() = testDeserializeNull(serializer)
    fun testDeserializeArray(expected: List<T>, actual: JsonArray) = testDeserializeArray(expected, actual, serializer)
}