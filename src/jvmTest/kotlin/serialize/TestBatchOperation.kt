package serialize

import com.algolia.search.saas.model.indexing.BatchOperation
import com.algolia.search.saas.model.indexing.BatchOperation.*
import com.algolia.search.saas.model.ObjectID
import com.algolia.search.saas.serialize.*
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestBatchOperation : TestSerializer<BatchOperation>(BatchOperation) {

    private val objectID = ObjectID("id")
    private val json = json {
        "objectID" to objectID.raw
    }

    override val items = listOf(
        AddObject(json) to json {
            KeyAction to KeyAddObject
            KeyBody to json
        },
        ReplaceObject(json, objectID) to json {
            KeyAction to KeyUpdateObject
            KeyBody to json
        },
        UpdateObject(json, objectID, true) to json {
            KeyAction to KeyPartialUpdateObject
            KeyBody to json
        },
        UpdateObject(json, objectID, false) to json {
            KeyAction to KeyPartialUpdateObjectNoCreate
            KeyBody to json
        },
        DeleteObject(objectID) to json {
            KeyAction to KeyDeleteObject
            KeyBody to json
        },
        DeleteIndex to json { KeyAction to KeyDelete },
        ClearIndex to json { KeyAction to KeyClear }
    )
}