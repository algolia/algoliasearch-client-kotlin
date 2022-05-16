package com.algolia.search.model.rule

import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import com.algolia.search.serialize.internal.jsonPrimitiveOrNull
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

@Serializable(Edit.Companion::class)
public data class Edit(
    /**
     * Text or patterns to remove from the [Query.query].
     */
    val delete: String,
    /**
     * Text that should be inserted in place of the removed text inside the [Query.query].
     */
    val insert: String? = null
) {

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(Edit::class)
    public companion object : KSerializer<Edit> {

        override fun serialize(encoder: Encoder, value: Edit) {
            val type = if (value.insert != null) Key.Replace else Key.RemoveLowercase
            val json = buildJsonObject {
                put(Key.Type, type.lowercase())
                put(Key.Delete, value.delete)
                value.insert?.let { put(Key.Insert, it) }
            }

            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): Edit {
            val json = decoder.asJsonInput().jsonObject

            return Edit(
                json.getValue(Key.Delete).jsonPrimitive.content,
                json[Key.Insert]?.jsonPrimitiveOrNull?.content
            )
        }
    }
}
