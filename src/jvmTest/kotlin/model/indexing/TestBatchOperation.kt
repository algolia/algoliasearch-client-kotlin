package model.indexing

import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation.*
import com.algolia.search.serialize.*
import kotlinx.serialization.json.json
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestBatchOperation {

    private val json = json {}
    private val objectID = ObjectID(unknown)

    @Test
    fun raw() {
        AddObject(json).raw shouldEqual KeyAddObject
        ReplaceObject(objectID, json).raw shouldEqual KeyUpdateObject
        DeleteObject(objectID).raw shouldEqual KeyDeleteObject
        UpdateObject(objectID, json).raw shouldEqual KeyPartialUpdateObject
        UpdateObject(objectID, json, false).raw shouldEqual KeyPartialUpdateObjectNoCreate
        ClearIndex.raw shouldEqual KeyClear
        DeleteIndex.raw shouldEqual KeyDelete
        Other(unknown, json).raw shouldEqual unknown
    }
}