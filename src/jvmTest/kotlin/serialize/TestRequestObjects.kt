package serialize

import attributeA
import attributeB
import com.algolia.search.model.ObjectID
import com.algolia.search.model.multiple_index.RequestObjects
import com.algolia.search.serialize.KeyAttributesToRetrieve
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyObjectID
import indexA
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestRequestObjects : TestSerializer<RequestObjects>(RequestObjects) {

    private val objectID = ObjectID("objectA")

    override val items = listOf(
        RequestObjects(indexA, objectID) to json {
            KeyIndexName to indexA.raw
            KeyObjectID to objectID.raw
        },
        RequestObjects(indexA, objectID, attributeA, attributeB) to json {
            KeyIndexName to indexA.raw
            KeyObjectID to objectID.raw
            KeyAttributesToRetrieve to jsonArray {
                +attributeA.raw
                +attributeB.raw
            }
        }
    )
}