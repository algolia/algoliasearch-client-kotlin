package com.algolia.search.model.queryrule

import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json


@Serializable(Edit.Companion::class)
data class Edit(val delete: String, val insert: String? = null) {

    @Serializer(Edit::class)
    companion object : KSerializer<Edit> {

        override fun serialize(encoder: Encoder, obj: Edit) {
            val type = if (obj.insert != null) KeyReplace else KeyRemoveLowercase
            val json = json {
                KeyType to type.toLowerCase()
                KeyDelete to obj.delete
                obj.insert?.let { KeyInsert to it }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): Edit {
            val json = decoder.asJsonInput().jsonObject

            return Edit(
                json[KeyDelete].content,
                json.getOrNull(KeyInsert)?.content
            )
        }
    }
}