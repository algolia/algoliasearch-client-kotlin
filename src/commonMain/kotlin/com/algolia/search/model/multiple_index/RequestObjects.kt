package com.algolia.search.model.multiple_index

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.*
import com.algolia.search.toAttribute
import com.algolia.search.toIndexName
import com.algolia.search.toObjectID
import kotlinx.serialization.*
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


@Serializable(RequestObjects.Companion::class)
data class RequestObjects internal constructor(
    val indexName: IndexName,
    val objectID: ObjectID,
    val attributes: List<Attribute>
) {

    constructor(
        indexName: IndexName,
        objectID: ObjectID,
        vararg attributes: Attribute
    ) : this(indexName, objectID, attributes.toList())

    @Serializer(RequestObjects::class)
    companion object : KSerializer<RequestObjects> {

        override fun serialize(encoder: Encoder, obj: RequestObjects) {
            val json = json {
                KeyIndex to obj.indexName.raw
                KeyObjectID to obj.objectID.raw
                if (obj.attributes.isNotEmpty()) {
                    KeyAttributesToRetrieve to jsonArray { obj.attributes.forEach { +it.raw } }
                }

            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): RequestObjects {
            val element = decoder.asJsonInput().jsonObject

            return RequestObjects(
                element.getPrimitive(KeyIndex).content.toIndexName(),
                element.getPrimitive(KeyObjectID).content.toObjectID(),
                element.getArrayOrNull(KeyAttributesToRetrieve)?.map { it.content.toAttribute() }.orEmpty()
            )
        }
    }
}