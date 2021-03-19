package model.indexing

import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation.AddObject
import com.algolia.search.model.indexing.BatchOperation.ClearIndex
import com.algolia.search.model.indexing.BatchOperation.DeleteIndex
import com.algolia.search.model.indexing.BatchOperation.DeleteObject
import com.algolia.search.model.indexing.BatchOperation.Other
import com.algolia.search.model.indexing.BatchOperation.PartialUpdateObject
import com.algolia.search.model.indexing.BatchOperation.ReplaceObject
import com.algolia.search.serialize.KeyAddObject
import com.algolia.search.serialize.KeyClear
import com.algolia.search.serialize.KeyDelete
import com.algolia.search.serialize.KeyDeleteObject
import com.algolia.search.serialize.KeyPartialUpdateObject
import com.algolia.search.serialize.KeyPartialUpdateObjectNoCreate
import com.algolia.search.serialize.KeyUpdateObject
import kotlinx.serialization.json.buildJsonObject
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestBatchOperation {

    private val json = buildJsonObject {}
    private val objectID = ObjectID(unknown)

    @Test
    fun raw() {
        AddObject(json).raw shouldEqual KeyAddObject
        ReplaceObject(objectID, json).raw shouldEqual KeyUpdateObject
        DeleteObject(objectID).raw shouldEqual KeyDeleteObject
        PartialUpdateObject(objectID, json).raw shouldEqual KeyPartialUpdateObject
        PartialUpdateObject(objectID, json, false).raw shouldEqual KeyPartialUpdateObjectNoCreate
        ClearIndex.raw shouldEqual KeyClear
        DeleteIndex.raw shouldEqual KeyDelete
        Other(unknown, json).raw shouldEqual unknown
    }
}
