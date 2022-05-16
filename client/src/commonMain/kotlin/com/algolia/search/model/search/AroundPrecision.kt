package com.algolia.search.model.search

import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

/**
 * Precision of geo search (in meters), to add grouping by geo location to the ranking formula.
 * When ranking hits, geo distances are grouped into ranges of aroundPrecision size.
 * All hits within the same range are considered equal with respect to the geo ranking parameter.
 * For example, if you set aroundPrecision to 100, any two objects lying in the range [0, 99m] from the searched
 * location will be considered equal; same for [100, 199], [200, 299], etc.
 */
@Serializable(AroundPrecision.Companion::class)
public sealed class AroundPrecision {

    public data class Int(val value: kotlin.Int) : AroundPrecision()

    public data class Ranges(val list: List<IntRange>) : AroundPrecision() {

        public constructor(vararg range: IntRange) : this(range.toList())
    }

    public data class Other(val raw: JsonElement) : AroundPrecision()

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(AroundPrecision::class)
    public companion object : KSerializer<AroundPrecision> {

        override fun serialize(encoder: Encoder, value: AroundPrecision) {
            val json = when (value) {
                is Int -> JsonPrimitive(value.value)
                is Ranges -> buildJsonArray {
                    value.list.forEach {
                        add(
                            buildJsonObject {
                                put(Key.From, it.first)
                                put(Key.Value, it.last)
                            }
                        )
                    }
                }
                is Other -> value.raw
            }
            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): AroundPrecision {
            return when (val json = decoder.asJsonInput()) {
                is JsonArray -> Ranges(
                    json.map {
                        val pair = it.jsonObject

                        IntRange(
                            pair.getValue(Key.From).jsonPrimitive.int,
                            pair.getValue(Key.Value).jsonPrimitive.int
                        )
                    }
                )
                is JsonPrimitive -> Int(json.int)
                else -> Other(json)
            }
        }
    }
}
