package com.algolia.search.model.indexing

import com.algolia.search.endpoint.EndpointIndex
import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.ObjectID
import com.algolia.search.model.Raw
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.KeyAction
import com.algolia.search.serialize.KeyAddObject
import com.algolia.search.serialize.KeyBody
import com.algolia.search.serialize.KeyClear
import com.algolia.search.serialize.KeyDelete
import com.algolia.search.serialize.KeyDeleteObject
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyPartialUpdateObject
import com.algolia.search.serialize.KeyPartialUpdateObjectNoCreate
import com.algolia.search.serialize.KeyUpdateObject
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import com.algolia.search.serialize.merge
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

/**
 * Operation that can be batched using [EndpointIndexing.batch] or [EndpointMultipleIndex.multipleBatchObjects]
 */
@Serializable(BatchOperation.Companion::class)
public sealed class BatchOperation(override val raw: String) : Raw<String> {

    /**
     *  Equivalent to [EndpointIndexing.saveObject] without an ID
     */
    public data class AddObject(
        val json: JsonObject
    ) : BatchOperation(KeyAddObject) {

        public companion object {

            public fun <T> from(serializer: KSerializer<T>, data: T): AddObject {
                return AddObject(Json.encodeToJsonElement(serializer, data).jsonObject)
            }
        }
    }

    /**
     *  Equivalent to [EndpointIndexing.replaceObject]
     */
    public data class ReplaceObject(
        val objectID: ObjectID,
        val json: JsonObject
    ) : BatchOperation(KeyUpdateObject) {

        public companion object {

            public fun <T : Indexable> from(serializer: KSerializer<T>, data: T): ReplaceObject {
                return ReplaceObject(data.objectID, Json.encodeToJsonElement(serializer, data).jsonObject)
            }
        }
    }

    /**
     * Equivalent to [EndpointIndexing.partialUpdateObject]
     */
    public data class PartialUpdateObject(
        val objectID: ObjectID,
        val json: JsonObject,
        val createIfNotExists: Boolean = true
    ) : BatchOperation(if (createIfNotExists) KeyPartialUpdateObject else KeyPartialUpdateObjectNoCreate) {

        public companion object {

            public fun <T : Indexable> from(
                serializer: KSerializer<T>,
                data: T,
                createIfNotExists: Boolean = true
            ): PartialUpdateObject {
                return PartialUpdateObject(
                    data.objectID,
                    Json.encodeToJsonElement(serializer, data).jsonObject,
                    createIfNotExists
                )
            }

            public fun from(
                objectID: ObjectID,
                partial: Partial,
                createIfNotExists: Boolean = true
            ): PartialUpdateObject {
                return PartialUpdateObject(
                    objectID,
                    buildJsonObject {
                        put(partial.attribute.raw, partial.value)
                        put(KeyObjectID, objectID.raw)
                    },
                    createIfNotExists
                )
            }
        }
    }

    /**
     * Equivalent to [EndpointIndexing.deleteObject]
     */
    public data class DeleteObject(val objectID: ObjectID) : BatchOperation(KeyDeleteObject)

    /**
     * Equivalent to [EndpointIndex.deleteIndex]
     */
    public object DeleteIndex : BatchOperation(KeyDelete)

    /**
     * Equivalent to [EndpointIndexing.clearObjects]
     */
    public object ClearIndex : BatchOperation(KeyClear)

    public data class Other(val key: String, val json: JsonObject) : BatchOperation(key)

    @Serializer(BatchOperation::class)
    public companion object : KSerializer<BatchOperation> {

        private fun batchJson(value: BatchOperation, block: JsonObjectBuilder.() -> Unit) = buildJsonObject {
            put(KeyAction, value.raw)
            block(this)
        }

        override fun serialize(encoder: Encoder, value: BatchOperation) {
            val json = when (value) {
                is AddObject -> batchJson(value) { put(KeyBody, value.json) }
                is ReplaceObject -> batchJson(value) {
                    put(KeyBody,
                        value.json.merge(
                            buildJsonObject {
                                put(KeyObjectID, value.objectID.raw)
                            }
                        )
                    )
                }
                is PartialUpdateObject -> batchJson(value) {
                    put(KeyBody,
                        value.json.merge(
                            buildJsonObject {
                                put(KeyObjectID, value.objectID.raw)
                            }
                        )
                    )
                }
                is DeleteObject -> batchJson(value) {
                    put(KeyBody, buildJsonObject { put(KeyObjectID, value.objectID.raw) })
                }
                is DeleteIndex -> batchJson(value) {}
                is ClearIndex -> batchJson(value) {}
                is Other -> batchJson(value) { put(KeyBody, value.json) }
            }

            encoder.asJsonOutput().encodeJsonElement(json)
        }

        private val JsonObject.body get() = this.getValue(KeyBody).jsonObject
        private val JsonObject.objectID get() = body.getValue(KeyObjectID).jsonPrimitive.content.toObjectID()

        override fun deserialize(decoder: Decoder): BatchOperation {
            val element = decoder.asJsonInput().jsonObject

            return when (val action = element.getValue(KeyAction).jsonPrimitive.content) {
                KeyAddObject -> AddObject(element.body)
                KeyUpdateObject -> ReplaceObject(element.objectID, element.body)
                KeyPartialUpdateObject -> PartialUpdateObject(element.objectID, element.body)
                KeyPartialUpdateObjectNoCreate -> PartialUpdateObject(element.objectID, element.body, false)
                KeyDeleteObject -> DeleteObject(element.objectID)
                KeyDelete -> DeleteIndex
                KeyClear -> ClearIndex
                else -> Other(action, element.body)
            }
        }
    }
}
