package com.algolia.search.model.indexing

import com.algolia.search.endpoint.EndpointIndex
import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.ObjectID
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import com.algolia.search.serialize.internal.merge
import kotlinx.serialization.ExperimentalSerializationApi
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
    ) : BatchOperation(Key.AddObject) {

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
    ) : BatchOperation(Key.UpdateObject) {

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
    ) : BatchOperation(if (createIfNotExists) Key.PartialUpdateObject else Key.PartialUpdateObjectNoCreate) {

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
                    Json.encodeToJsonElement(Partial, partial).jsonObject.merge(
                        buildJsonObject {
                            put(Key.ObjectID, objectID.raw)
                        }
                    ),
                    createIfNotExists
                )
            }
        }
    }

    /**
     * Equivalent to [EndpointIndexing.deleteObject]
     */
    public data class DeleteObject(val objectID: ObjectID) : BatchOperation(Key.DeleteObject)

    /**
     * Equivalent to [EndpointIndex.deleteIndex]
     */
    public object DeleteIndex : BatchOperation(Key.Delete)

    /**
     * Equivalent to [EndpointIndexing.clearObjects]
     */
    public object ClearIndex : BatchOperation(Key.Clear)

    public data class Other(val key: String, val json: JsonObject) : BatchOperation(key)

    @Serializer(BatchOperation::class)
    @OptIn(ExperimentalSerializationApi::class)
    public companion object : KSerializer<BatchOperation> {

        private fun batchJson(value: BatchOperation, block: JsonObjectBuilder.() -> Unit) = buildJsonObject {
            put(Key.Action, value.raw)
            block(this)
        }

        override fun serialize(encoder: Encoder, value: BatchOperation) {
            val json = when (value) {
                is AddObject -> batchJson(value) { put(Key.Body, value.json) }
                is ReplaceObject -> batchJson(value) {
                    put(
                        Key.Body,
                        value.json.merge(
                            buildJsonObject {
                                put(Key.ObjectID, value.objectID.raw)
                            }
                        )
                    )
                }
                is PartialUpdateObject -> batchJson(value) {
                    put(
                        Key.Body,
                        value.json.merge(
                            buildJsonObject {
                                put(Key.ObjectID, value.objectID.raw)
                            }
                        )
                    )
                }
                is DeleteObject -> batchJson(value) {
                    put(Key.Body, buildJsonObject { put(Key.ObjectID, value.objectID.raw) })
                }
                is DeleteIndex -> batchJson(value) {}
                is ClearIndex -> batchJson(value) {}
                is Other -> batchJson(value) { put(Key.Body, value.json) }
            }

            encoder.asJsonOutput().encodeJsonElement(json)
        }

        private val JsonObject.body get() = this.getValue(Key.Body).jsonObject
        private val JsonObject.objectID get() = body.getValue(Key.ObjectID).jsonPrimitive.content.toObjectID()

        override fun deserialize(decoder: Decoder): BatchOperation {
            val element = decoder.asJsonInput().jsonObject

            return when (val action = element.getValue(Key.Action).jsonPrimitive.content) {
                Key.AddObject -> AddObject(element.body)
                Key.UpdateObject -> ReplaceObject(element.objectID, element.body)
                Key.PartialUpdateObject -> PartialUpdateObject(element.objectID, element.body)
                Key.PartialUpdateObjectNoCreate -> PartialUpdateObject(element.objectID, element.body, false)
                Key.DeleteObject -> DeleteObject(element.objectID)
                Key.Delete -> DeleteIndex
                Key.Clear -> ClearIndex
                else -> Other(action, element.body)
            }
        }
    }
}
