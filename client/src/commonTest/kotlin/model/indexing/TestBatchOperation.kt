package model.indexing

import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation.AddObject
import com.algolia.search.model.indexing.BatchOperation.ClearIndex
import com.algolia.search.model.indexing.BatchOperation.DeleteIndex
import com.algolia.search.model.indexing.BatchOperation.DeleteObject
import com.algolia.search.model.indexing.BatchOperation.Other
import com.algolia.search.model.indexing.BatchOperation.PartialUpdateObject
import com.algolia.search.model.indexing.BatchOperation.ReplaceObject
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.buildJsonObject
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestBatchOperation {

    private val json = buildJsonObject {}
    private val objectID = ObjectID(unknown)

    @Test
    fun raw() {
        AddObject(json).raw shouldEqual Key.AddObject
        ReplaceObject(objectID, json).raw shouldEqual Key.UpdateObject
        DeleteObject(objectID).raw shouldEqual Key.DeleteObject
        PartialUpdateObject(objectID, json).raw shouldEqual Key.PartialUpdateObject
        PartialUpdateObject(objectID, json, false).raw shouldEqual Key.PartialUpdateObjectNoCreate
        ClearIndex.raw shouldEqual Key.Clear
        DeleteIndex.raw shouldEqual Key.Delete
        Other(unknown, json).raw shouldEqual unknown
    }
}
