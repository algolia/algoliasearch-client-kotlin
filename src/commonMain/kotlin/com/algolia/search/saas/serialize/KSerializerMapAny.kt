package com.algolia.search.saas.serialize

import kotlinx.serialization.Encoder
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.internal.HashMapClassDesc
import kotlinx.serialization.internal.SerialClassDescImpl
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.*


internal object KSerializerMapAny : SerializationStrategy<Map<String, Any?>> {

    override val descriptor: SerialDescriptor = HashMapClassDesc(
        StringSerializer.descriptor,
        SerialClassDescImpl("any")
    )

    private fun JsonArrayBuilder.serialize(any: Any?) {
        if (any != null) {
            when (any) {
                is String -> +any
                is Number -> +any
                is Boolean -> +any
                is Collection<*> -> +jsonArray { any.forEach { serialize(it) } }
                is Array<*> -> +jsonArray { any.forEach { serialize(it) } }
                else -> any.toString()
            }
        } else +JsonNull
    }

    private fun JsonObjectBuilder.serialize(entry: Pair<String, Any?>) {
        val (key, value) = entry
        if (value != null) {
            when (value) {
                is String -> key to value
                is Number -> key to value
                is Boolean -> key to value
                is Collection<*> -> key to jsonArray { value.forEach { serialize(it) } }
                is Array<*> -> key to jsonArray { value.forEach { serialize(it) } }
                is JsonElement -> key to value
                is Map<*, *> -> key to json { value.forEach { serialize(it.key.toString() to it.value) } }
                else -> key to value.toString()
            }
        } else key to JsonNull
    }

    override fun serialize(encoder: Encoder, obj: Map<String, Any?>) {
        val json = json {
            obj.forEach { (key, value) ->
                serialize(key to value)
            }
        }
        encoder.asJsonOutput().encodeJson(json)
    }
}


