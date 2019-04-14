package com.algolia.search.model.indexing

import com.algolia.search.helper.toObjectID
import com.algolia.search.model.ObjectID
import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.json


@Serializable(BatchOperation.Companion::class)
public sealed class BatchOperation(override val raw: String) : Raw<String> {

    public data class AddObject(
        val json: JsonObject
    ) : BatchOperation(KeyAddObject) {

        companion object {

            public fun <T> from(serializer: KSerializer<T>, data: T): AddObject {
                return AddObject(Json.plain.toJson(serializer, data).jsonObject)
            }
        }
    }

    public data class ReplaceObject(
        val objectID: ObjectID,
        val json: JsonObject
    ) : BatchOperation(KeyUpdateObject) {

        companion object {

            public fun <T : Indexable> from(serializer: KSerializer<T>, data: T): ReplaceObject {
                return ReplaceObject(data.objectID, Json.plain.toJson(serializer, data).jsonObject)
            }
        }
    }

    public data class UpdateObject(
        val objectID: ObjectID,
        val json: JsonObject,
        val createIfNotExists: Boolean = true
    ) : BatchOperation(if (createIfNotExists) KeyPartialUpdateObject else KeyPartialUpdateObjectNoCreate) {

        companion object {

            public fun <T : Indexable> from(
                serializer: KSerializer<T>,
                data: T,
                createIfNotExists: Boolean = true
            ): UpdateObject {
                return UpdateObject(data.objectID, Json.plain.toJson(serializer, data).jsonObject, createIfNotExists)
            }

            public fun from(
                objectID: ObjectID,
                partial: Partial,
                createIfNotExists: Boolean = true
            ): UpdateObject {
                return UpdateObject(
                    objectID,
                    Json.plain.toJson(Partial, partial).jsonObject,
                    createIfNotExists
                )
            }
        }
    }

    public data class DeleteObject(val objectID: ObjectID) : BatchOperation(KeyDeleteObject)

    public object DeleteIndex : BatchOperation(KeyDelete)

    public object ClearIndex : BatchOperation(KeyClear)

    public data class Other(val key: String, val json: JsonObject) : BatchOperation(key)

    @Serializer(BatchOperation::class)
    companion object : KSerializer<BatchOperation> {

        private fun batchJson(obj: BatchOperation, block: JsonObjectBuilder.() -> Unit) = json {
            KeyAction to obj.raw
            block(this)
        }

        override fun serialize(encoder: Encoder, obj: BatchOperation) {
            val json = when (obj) {
                is AddObject -> batchJson(obj) { KeyBody to obj.json }
                is ReplaceObject -> batchJson(obj) { KeyBody to obj.json.merge(json { KeyObjectID to obj.objectID.raw }) }
                is UpdateObject -> batchJson(obj) { KeyBody to obj.json.merge(json { KeyObjectID to obj.objectID.raw }) }
                is DeleteObject -> batchJson(obj) { KeyBody to json { KeyObjectID to obj.objectID.raw } }
                is DeleteIndex -> batchJson(obj) {}
                is ClearIndex -> batchJson(obj) {}
                is Other -> batchJson(obj) { KeyBody to obj.json }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        private val JsonObject.body get() = this.getObject(KeyBody)
        private val JsonObject.objectID get() = body.getPrimitive(KeyObjectID).content.toObjectID()

        override fun deserialize(decoder: Decoder): BatchOperation {
            val element = decoder.asJsonInput().jsonObject

            return when (val action = element.getPrimitive(KeyAction).content) {
                KeyAddObject -> AddObject(element.body)
                KeyUpdateObject -> ReplaceObject(element.objectID, element.body)
                KeyPartialUpdateObject -> UpdateObject(element.objectID, element.body)
                KeyPartialUpdateObjectNoCreate -> UpdateObject(element.objectID, element.body, false)
                KeyDeleteObject -> DeleteObject(element.objectID)
                KeyDelete -> DeleteIndex
                KeyClear -> ClearIndex
                else -> Other(action, element.body)
            }
        }
    }
}