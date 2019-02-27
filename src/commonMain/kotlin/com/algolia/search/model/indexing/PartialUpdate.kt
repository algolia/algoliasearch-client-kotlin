package com.algolia.search.model.indexing

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.json


public sealed class PartialUpdate(
    open val attribute: Attribute,
    internal open val value: Value<*>
) {

    internal sealed class Value<T> {

        abstract val raw: T

        data class String(override val raw: kotlin.String) : Value<kotlin.String>()

        data class Number(override val raw: kotlin.Number) : Value<kotlin.Number>()
    }

    public data class Increment internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : PartialUpdate(attribute, value) {

        public constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    public data class Decrement internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : PartialUpdate(attribute, value) {

        public constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    public data class Add internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : PartialUpdate(attribute, value) {

        public constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    public data class Remove internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : PartialUpdate(attribute, value) {

        public constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    public data class AddUnique internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : PartialUpdate(attribute, value) {

        public constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    @Serializer(PartialUpdate::class)
    internal companion object : KSerializer<PartialUpdate> {

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
            val operation = element.getObject(key).getPrimitive(Key_Operation).content
            val raw = element.getObject(key).getPrimitive(KeyValue)
            val int = raw.intOrNull?.let { Value.Number(it) }
            val double = raw.doubleOrNull?.let { Value.Number(it) }
            val value = int ?: double ?: Value.String(raw.content)

            return when (operation) {
                KeyIncrement -> Increment(attribute, value)
                KeyDecrement -> Decrement(attribute, value)
                KeyAdd -> Add(attribute, value)
                KeyRemove -> Remove(attribute, value)
                KeyAddUnique -> AddUnique(attribute, value)
                else -> throw Exception("Unknown operation $operation")
            }
        }
    }
}

