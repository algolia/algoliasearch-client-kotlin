package com.algolia.search.model.queryrule

import com.algolia.search.serialize.KeyEdits
import com.algolia.search.serialize.KeyRemoveLowercase
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Serializable(QueryOrEdits.Companion::class)
sealed class QueryOrEdits {

    data class Query(val query: String) : QueryOrEdits()

    data class Edits(val edits: List<Edit>) : QueryOrEdits()

    @Serializer(QueryOrEdits::class)
    companion object : KSerializer<QueryOrEdits> {

        override fun serialize(encoder: Encoder, obj: QueryOrEdits) {
            val json = when (obj) {
                is Query -> JsonLiteral(obj.query)
                is Edits -> json { KeyEdits to Json.plain.toJson(Edit.list, obj.edits) }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): QueryOrEdits {
            val json = decoder.asJsonInput()

            return when (json) {
                is JsonPrimitive -> Query(json.content)
                is JsonObject -> {
                    val edits = if (json.containsKey(KeyEdits)) {
                        Json.plain.fromJson(
                            Edit.list,
                            json.jsonObject[KeyEdits]
                        )
                    } else json[KeyRemoveLowercase].jsonArray.map { Edit(it.content) }
                    Edits(edits)
                }
                else -> throw Exception("Unable to deserialize QueryRule.")
            }
        }
    }
}