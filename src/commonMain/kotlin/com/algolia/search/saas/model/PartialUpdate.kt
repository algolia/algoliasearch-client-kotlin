package com.algolia.search.saas.model

import com.algolia.search.saas.serialize.*
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.content
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.json


internal sealed class Value<T> {

    abstract val raw: T

    data class String(override val raw: kotlin.String) : Value<kotlin.String>()

    data class Number(override val raw: kotlin.Number) : Value<kotlin.Number>()
}

sealed class PartialUpdate(
    open val attribute: Attribute,
    internal open val value: Value<*>
) {

    data class Increment internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : PartialUpdate(attribute, value) {

        constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    data class Decrement internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : PartialUpdate(attribute, value) {

        constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    data class Add internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : PartialUpdate(attribute, value) {

        constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    data class Remove internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : PartialUpdate(attribute, value) {

        constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    data class AddUnique internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : PartialUpdate(attribute, value) {

        constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    @Serializer(PartialUpdate::class)
    companion object : KSerializer<PartialUpdate> {

        override fun serialize(encoder: Encoder, obj: PartialUpdate) {
            val key = when (obj) {
                is Increment -> KeyIncrement
                is Decrement -> KeyDecrement
                is Add -> KeyAdd
                is Remove -> KeyRemove
                is AddUnique -> KeyAddUnique
            }
            val json = json {
                obj.attribute.raw to json {
                    Key_Operation to key
                    when (val value = obj.value) {
                        is Value.String -> KeyValue to value.raw
                        is Value.Number -> KeyValue to value.raw
                    }
                }
            }
            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): PartialUpdate {
            val element = decoder.asJsonInput().jsonObject
            val key = element.keys.first()
            val attribute = key.toAttribute()
            val operation = element.getObject(key)[Key_Operation].content
            val raw = element.getObject(key)[KeyValue]
            val int = raw.intOrNull?.let { Value.Number(it) }
            val double = raw.doubleOrNull?.let { Value.Number(it) }
            val value = int ?: double ?: Value.String(raw.content)

            return when (operation) {
                KeyIncrement -> PartialUpdate.Increment(attribute, value)
                KeyDecrement -> PartialUpdate.Decrement(attribute, value)
                KeyAdd -> PartialUpdate.Add(attribute, value)
                KeyRemove -> PartialUpdate.Remove(attribute, value)
                KeyAddUnique -> PartialUpdate.AddUnique(attribute, value)
                else -> throw Exception("Unknown operation $operation")
            }
        }
    }
}

