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
import com.algolia.search.serialize.internal.Key
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
            put(Key.Action, Key.AddObject)
            put(Key.Body, jsonObject)
        },
        ReplaceObject(objectID, jsonObject) to buildJsonObject {
            put(Key.Action, Key.UpdateObject)
            put(Key.Body, jsonObject)
        },
        PartialUpdateObject(objectID, jsonObject, true) to buildJsonObject {
            put(Key.Action, Key.PartialUpdateObject)
            put(Key.Body, jsonObject)
        },
        PartialUpdateObject(objectID, jsonObject, false) to buildJsonObject {
            put(Key.Action, Key.PartialUpdateObjectNoCreate)
            put(Key.Body, jsonObject)
        },
        PartialUpdateObject.from(
            ObjectID(unknown),
            Partial.Update(
                Attribute("key"), "value"
            )
        ) to buildJsonObject {
            put(Key.Action, Key.PartialUpdateObject)
            put(
                Key.Body,
                buildJsonObject {
                    put(Key.ObjectID, unknown)
                    put("key", "value")
                }
            )
        },
        DeleteObject(objectID) to buildJsonObject {
            put(Key.Action, Key.DeleteObject)
            put(Key.Body, jsonObject)
        },
        DeleteIndex to buildJsonObject { put(Key.Action, Key.Delete) },
        ClearIndex to buildJsonObject { put(Key.Action, Key.Clear) },
        Other(unknown, jsonObject) to buildJsonObject {
            put(Key.Action, unknown)
            put(Key.Body, jsonObject)
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
