package com.algolia.search.model.search

import com.algolia.search.serialize.KeyFrom
import com.algolia.search.serialize.KeyValue
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Serializable(AroundPrecision.Companion::class)
public sealed class AroundPrecision {

    public data class Int(val value: kotlin.Int) : AroundPrecision()

    public data class Ranges(val list: List<IntRange>) : AroundPrecision() {

        public constructor(vararg range: IntRange) : this(range.toList())
    }

    public data class Other(val raw: JsonElement) : AroundPrecision()

    @Serializer(AroundPrecision::class)
    internal companion object : KSerializer<AroundPrecision> {

        override fun serialize(encoder: Encoder, obj: AroundPrecision) {
            val json = when (obj) {
                is Int -> JsonLiteral(obj.value)
                is Ranges -> jsonArray {
                    obj.list.forEach {
                        +json {
                            KeyFrom to it.first
                            KeyValue to it.endInclusive
                        }
                    }
                }
                is Other -> obj.raw
            }
            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): AroundPrecision {
            val json = decoder.asJsonInput()

            return when (json) {
                is JsonArray -> Ranges(json.map {
                    val pair = it.jsonObject

                    IntRange(
                        pair.getPrimitive(KeyFrom).int,
                        pair.getPrimitive(KeyValue).int
                    )
                })
                is JsonLiteral -> Int(json.int)
                else -> Other(json)
            }
        }
    }
}