package com.algolia.search.model.queryrule

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Serializable(Consequence.Companion::class)
data class Consequence(
    @Optional val params: Params? = null,
    @Optional val promote: List<Promotion>? = null,
    @Optional val hide: List<ObjectID>? = null,
    @Optional val userData: JsonObject? = null
) {

    @Serializer(Consequence::class)
    companion object : KSerializer<Consequence> {

        override fun serialize(encoder: Encoder, obj: Consequence) {
            val json = json {
                obj.params?.let { KeyParams to it.toJsonObject(Params.serializer()).encodeNoNulls() }
                obj.promote?.let { KeyPromote to Json.plain.toJson(Promotion.serializer().list, it) }
                obj.hide?.let { KeyHide to jsonArray { it.forEach { +json { KeyObjectID to it.raw } } } }
                obj.userData?.let { KeyUserData to it.encodeNoNulls() }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): Consequence {
            val json = decoder.asJsonInput().jsonObject

            return Consequence(
                json.getObjectOrNull(KeyParams)?.let { Json.nonstrict.fromJson(Params.serializer(), it) },
                json.getArrayOrNull(KeyPromote)?.let { Json.plain.fromJson(Promotion.serializer().list, it) },
                json.getArrayOrNull(KeyHide)?.let { it.map { ObjectID(it.jsonObject[KeyObjectID].content) } },
                json.getObjectOrNull(KeyUserData)
            )
        }
    }
}