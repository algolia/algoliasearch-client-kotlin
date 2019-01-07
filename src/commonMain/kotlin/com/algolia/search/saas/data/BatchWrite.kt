package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import com.algolia.search.saas.toObjectID
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Serializable(BatchWrite.Companion::class)
sealed class BatchWrite(override val raw: String) : Raw<String> {

    data class AddObject(
        val json: JsonObject
    ) : BatchWrite(KeyAddObject) {

        companion object {

            fun <T> from(data: T, serializer: KSerializer<T>): AddObject {
                return AddObject(Json.plain.toJson(data, serializer).jsonObject)
            }
        }
    }

    data class ReplaceObject(
        val json: JsonObject,
        val objectID: ObjectID
    ) : BatchWrite(KeyUpdateObject) {

        companion object {

            fun <T : Indexable> from(data: T, serializer: KSerializer<T>): ReplaceObject {
                return ReplaceObject(Json.plain.toJson(data, serializer).jsonObject, data.objectID)
            }
        }
    }

    data class PartialUpdateObject(
        val json: JsonObject,
        val objectID: ObjectID,
        val createIfNotExists: Boolean = true
    ) : BatchWrite(if (createIfNotExists) KeyPartialUpdateObject else KeyPartialUpdateObjectNoCreate) {

        companion object {

            fun <T : Indexable> from(
                data: T,
                serializer: KSerializer<T>,
                createIfNotExists: Boolean = true
            ): PartialUpdateObject {
                return PartialUpdateObject(
                    Json.plain.toJson(data, serializer).jsonObject,
                    data.objectID,
                    createIfNotExists
                )
            }

            fun from(
                objectID: ObjectID,
                partialUpdate: PartialUpdate,
                createIfNotExists: Boolean
            ): PartialUpdateObject {
                return PartialUpdateObject(
                    Json.plain.toJson(partialUpdate, PartialUpdate).jsonObject,
                    objectID,
                    createIfNotExists
                )
            }
        }
    }

    data class DeleteObject(val objectID: ObjectID) : BatchWrite(KeyDeleteObject)

    object DeleteIndex : BatchWrite(KeyDelete)

    object ClearIndex : BatchWrite(KeyClear)

    @Serializer(BatchWrite::class)
    internal companion object : KSerializer<BatchWrite> {

        private fun batchJson(obj: BatchWrite, block: JsonObjectBuilder.() -> Unit) = json {
            KeyAction to obj.raw
            block(this)
        }

        override fun serialize(encoder: Encoder, obj: BatchWrite) {
            val json = when (obj) {
                is AddObject -> batchJson(obj) { KeyBody to obj.json }
                is ReplaceObject -> batchJson(obj) { KeyBody to obj.json.apply { KeyObjectId to obj.objectID } }
                is PartialUpdateObject -> batchJson(obj) { KeyBody to obj.json.apply { KeyObjectId to obj.objectID } }
                is DeleteObject -> batchJson(obj) { KeyBody to json { KeyObjectId to obj.objectID.raw } }
                is DeleteIndex -> batchJson(obj) {}
                is ClearIndex -> batchJson(obj) {}
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        private val JsonObject.body get() = this[KeyBody].jsonObject
        private val JsonObject.objectID get() = body[KeyObjectId].content.toObjectID()

        override fun deserialize(decoder: Decoder): BatchWrite {
            val element = decoder.asJsonInput().jsonObject

            return when (val action = element[KeyAction].content) {
                KeyAddObject -> AddObject(element.body)
                KeyUpdateObject -> ReplaceObject(element.body, element.objectID)
                KeyPartialUpdateObject -> PartialUpdateObject(element.body, element.objectID)
                KeyPartialUpdateObjectNoCreate -> PartialUpdateObject(element.body, element.objectID, false)
                KeyDeleteObject -> DeleteObject(element.objectID)
                KeyDelete -> DeleteIndex
                KeyClear -> ClearIndex
                else -> throw Exception("Unknown batch write operation $action")
            }
        }
    }
}