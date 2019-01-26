package data

import com.algolia.search.saas.model.indexing.BatchOperation.*
import com.algolia.search.saas.model.ObjectID
import com.algolia.search.saas.serialize.*
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
        ReplaceObject(json, objectID).raw shouldEqual KeyUpdateObject
        DeleteObject(objectID).raw shouldEqual KeyDeleteObject
        UpdateObject(json, objectID).raw shouldEqual KeyPartialUpdateObject
        UpdateObject(json, objectID, false).raw shouldEqual KeyPartialUpdateObjectNoCreate
        ClearIndex.raw shouldEqual KeyClear
        DeleteIndex.raw shouldEqual KeyDelete
    }
}