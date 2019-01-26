package com.algolia.search.saas.model

import com.algolia.search.saas.serialize.*
import com.algolia.search.saas.toObjectID
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Serializable(BatchOperation.Companion::class)
sealed class BatchOperation(override val raw: String) : Raw<String> {

    data class AddObject(
        val json: JsonObject
    ) : BatchOperation(KeyAddObject) {

        companion object {

            fun <T> from(data: T, serializer: KSerializer<T>): AddObject {
                return AddObject(Json.plain.toJson(serializer, data).jsonObject)
            }
        }
    }

    data class ReplaceObject(
        val json: JsonObject,
        val objectID: ObjectID
    ) : BatchOperation(KeyUpdateObject) {

        companion object {

            fun <T : Indexable> from(data: T, serializer: KSerializer<T>): ReplaceObject {
                return ReplaceObject(Json.plain.toJson(serializer, data).jsonObject, data.objectID)
            }
        }
    }

    data class UpdateObject(
        val json: JsonObject,
        val objectID: ObjectID,
        val createIfNotExists: Boolean = true
    ) : BatchOperation(if (createIfNotExists) KeyPartialUpdateObject else KeyPartialUpdateObjectNoCreate) {

        companion object {

            fun <T : Indexable> from(
                data: T,
                serializer: KSerializer<T>,
                createIfNotExists: Boolean = true
            ): UpdateObject {
                return UpdateObject(
                    Json.plain.toJson(serializer, data).jsonObject,
                    data.objectID,
                    createIfNotExists
                )
            }

            fun from(
                partialUpdate: PartialUpdate,
                objectID: ObjectID,
                createIfNotExists: Boolean
            ): UpdateObject {
                return UpdateObject(
                    Json.plain.toJson(PartialUpdate, partialUpdate).jsonObject,
                    objectID,
                    createIfNotExists
                )
            }
        }
    }

    data class DeleteObject(val objectID: ObjectID) : BatchOperation(KeyDeleteObject)

    object DeleteIndex : BatchOperation(KeyDelete)

    object ClearIndex : BatchOperation(KeyClear)

    @Serializer(BatchOperation::class)
    companion object : KSerializer<BatchOperation> {

        private fun batchJson(obj: BatchOperation, block: JsonObjectBuilder.() -> Unit) = json {
            KeyAction to obj.raw
            block(this)
        }

        override fun serialize(encoder: Encoder, obj: BatchOperation) {
            val json = when (obj) {
                is AddObject -> batchJson(obj) { KeyBody to obj.json }
                is ReplaceObject -> batchJson(obj) { KeyBody to obj.json.apply { KeyObjectID to obj.objectID } }
                is UpdateObject -> batchJson(obj) { KeyBody to obj.json.apply { KeyObjectID to obj.objectID } }
                is DeleteObject -> batchJson(obj) { KeyBody to json { KeyObjectID to obj.objectID.raw } }
                is DeleteIndex -> batchJson(obj) {}
                is ClearIndex -> batchJson(obj) {}
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        private val JsonObject.body get() = this[KeyBody].jsonObject
        private val JsonObject.objectID get() = body[KeyObjectID].content.toObjectID()

        override fun deserialize(decoder: Decoder): BatchOperation {
            val element = decoder.asJsonInput().jsonObject

            return when (val action = element[KeyAction].content) {
                KeyAddObject -> AddObject(element.body)
                KeyUpdateObject -> ReplaceObject(element.body, element.objectID)
                KeyPartialUpdateObject -> UpdateObject(element.body, element.objectID)
                KeyPartialUpdateObjectNoCreate -> UpdateObject(element.body, element.objectID, false)
                KeyDeleteObject -> DeleteObject(element.objectID)
                KeyDelete -> DeleteIndex
                KeyClear -> ClearIndex
                else -> throw Exception("Unknown batch write operation $action")
            }
        }
    }
}