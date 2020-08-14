package serialize.indexing

import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.BatchOperation.AddObject
import com.algolia.search.model.indexing.BatchOperation.ClearIndex
import com.algolia.search.model.indexing.BatchOperation.DeleteIndex
import com.algolia.search.model.indexing.BatchOperation.DeleteObject
import com.algolia.search.model.indexing.BatchOperation.Other
import com.algolia.search.model.indexing.BatchOperation.PartialUpdateObject
import com.algolia.search.model.indexing.BatchOperation.ReplaceObject
import com.algolia.search.model.indexing.Partial
import com.algolia.search.serialize.internal.Json
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
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestBatchOperation : TestSerializer<BatchOperation>(BatchOperation) {

    private val objectID = ObjectID("id")
    private val jsonObject = buildJsonObject {
        put("objectID", objectID.raw)
    }

    override val items = listOf(
        AddObject(jsonObject) to buildJsonObject {
            put(KeyAction, KeyAddObject)
            put(KeyBody, jsonObject)
        },
        ReplaceObject(objectID, jsonObject) to buildJsonObject {
            put(KeyAction, KeyUpdateObject)
            put(KeyBody, jsonObject)
        },
        PartialUpdateObject(objectID, jsonObject, true) to buildJsonObject {
            put(KeyAction, KeyPartialUpdateObject)
            put(KeyBody, jsonObject)
        },
        PartialUpdateObject(objectID, jsonObject, false) to buildJsonObject {
            put(KeyAction, KeyPartialUpdateObjectNoCreate)
            put(KeyBody, jsonObject)
        },
        PartialUpdateObject.from(
            ObjectID(unknown),
            Partial.Update(
                Attribute("key"), "value"
            )
        ) to buildJsonObject {
            put(KeyAction, KeyPartialUpdateObject)
            put(
                KeyBody,
                buildJsonObject {
                    put(KeyObjectID, unknown)
                    put("key", "value")
                }
            )
        },
        DeleteObject(objectID) to buildJsonObject {
            put(KeyAction, KeyDeleteObject)
            put(KeyBody, jsonObject)
        },
        DeleteIndex to buildJsonObject { put(KeyAction, KeyDelete) },
        ClearIndex to buildJsonObject { put(KeyAction, KeyClear) },
        Other(unknown, jsonObject) to buildJsonObject {
            put(KeyAction, unknown)
            put(KeyBody, jsonObject)
        }
    )

    @Test
    fun fromPartialUpdate() {
        val fromPartialUpdate = PartialUpdateObject.from(ObjectID(unknown), Partial.Update(Attribute("key"), "value"))
        val partialUpdateObject = PartialUpdateObject(ObjectID(unknown), buildJsonObject { put("key", "value") })

        Json.encodeToJsonElement(BatchOperation, fromPartialUpdate) shouldEqual Json.encodeToJsonElement(
            BatchOperation, partialUpdateObject
        )
    }
}
