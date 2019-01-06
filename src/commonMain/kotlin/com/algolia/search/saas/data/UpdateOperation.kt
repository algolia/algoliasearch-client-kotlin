package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.json


internal sealed class Value<T> {

    abstract val raw: T

    data class String(override val raw: kotlin.String) : Value<kotlin.String>()

    data class Number(override val raw: kotlin.Number) : Value<kotlin.Number>()
}

sealed class UpdateOperation(
    open val attribute: Attribute,
    internal open val value: Value<*>
) {

    data class Increment(
        override val attribute: Attribute,
        val number: Number
    ) : UpdateOperation(attribute, Value.Number(number))

    data class Decrement internal constructor(
        override val attribute: Attribute,
        val number: Number
    ) : UpdateOperation(attribute, Value.Number(number))

    data class Add internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : UpdateOperation(attribute, value) {

        constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    data class Remove internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : UpdateOperation(attribute, value) {

        constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    data class AddUnique internal constructor(
        override val attribute: Attribute,
        override val value: Value<*>
    ) : UpdateOperation(attribute, value) {

        constructor(attribute: Attribute, value: String) : this(attribute, Value.String(value))

        constructor(attribute: Attribute, value: Number) : this(attribute, Value.Number(value))
    }

    @Serializer(UpdateOperation::class)
    internal companion object : KSerializer<UpdateOperation> {

        private fun toJson(operation: String, value: Value<*>) = json {
            Key_Operation to operation
            when (value) {
                is Value.String -> KeyValue to value.raw
                is Value.Number -> KeyValue to value.raw
            }
        }

        override fun serialize(encoder: Encoder, obj: UpdateOperation) {
            val key = when (obj) {
                is Increment -> KeyIncrement
                is Decrement -> KeyDecrement
                is Add -> KeyAdd
                is Remove -> KeyRemove
                is AddUnique -> KeyAddUnique
            }
            encoder.asJsonOutput().encodeJson(toJson(key, obj.value))
        }
    }
}

