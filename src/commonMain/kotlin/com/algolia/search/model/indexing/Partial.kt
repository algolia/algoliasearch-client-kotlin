package com.algolia.search.model.indexing

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.json


public sealed class Partial {

    abstract val attribute: Attribute
    internal abstract val value: Value<*>

    internal sealed class Value<T> {

        abstract val raw: T

        data class String(override val raw: kotlin.String) : Value<kotlin.String>()

        data class Number(override val raw: kotlin.Number) : Value<kotlin.Number>()
    }

    public data class Update internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : Partial() {

        public constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    public data class Increment internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : Partial() {

        public constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    public data class Decrement internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : Partial() {

        public constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    public data class Add internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : Partial() {

        public constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    public data class Remove internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : Partial() {

        public constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    public data class AddUnique internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : Partial() {

        public constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        public constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    @Serializer(Partial::class)
    internal companion object : KSerializer<Partial> {

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
                    when (val value = obj.value) {
                        is Value.String -> KeyValue to value.raw
                        is Value.Number -> KeyValue to value.raw
                    }
                }
            }
            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): Partial {
            val element = decoder.asJsonInput().jsonObject
            val key = element.keys.first()
            val attribute = key.toAttribute()
            val operation = element.getObject(key).getPrimitiveOrNull(Key_Operation)?.content
            val raw = element.getObject(key).getPrimitive(KeyValue)
            val int = raw.intOrNull?.let { Value.Number(it) }
            val double = raw.doubleOrNull?.let { Value.Number(it) }
            val value = int ?: double ?: Value.String(raw.content)

            return when (operation) {
                null -> Update(attribute, value)
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

