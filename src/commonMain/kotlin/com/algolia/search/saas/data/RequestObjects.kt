package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import com.algolia.search.saas.toAttribute
import com.algolia.search.saas.toIndexName
import com.algolia.search.saas.toObjectId
import kotlinx.serialization.*
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray


@Serializable(RequestObjects.Companion::class)
data class RequestObjects internal constructor(
    val indexName: IndexName,
    val objectID: ObjectId,
    val attributes: List<Attribute>
) {

    constructor(
        indexName: IndexName,
        objectID: ObjectId,
        vararg attributes: Attribute
    ) : this(indexName, objectID, attributes.toList())

    @Serializer(RequestObjects::class)
    internal companion object : KSerializer<RequestObjects> {

        override fun serialize(encoder: Encoder, obj: RequestObjects) {
            val json = json {
                KeyIndex_Name to obj.indexName.raw
                KeyObjectId to obj.objectID.raw
                KeyAttributesToRetrieve to jsonArray { obj.attributes.forEach { +it.raw } }

            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): RequestObjects {
            val element = decoder.asJsonInput().jsonObject

            return RequestObjects(
                element.getPrimitive(KeyIndex_Name).content.toIndexName(),
                element.getPrimitive(KeyObjectId).content.toObjectId(),
                element.getArray(KeyAttributesToRetrieve).map { it.content.toAttribute() }
            )
        }
    }
}