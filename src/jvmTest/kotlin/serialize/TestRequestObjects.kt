package serialize

import attributeA
import attributeB
import com.algolia.search.saas.data.ObjectId
import com.algolia.search.saas.data.RequestObjects
import com.algolia.search.saas.serialize.KeyAttributesToRetrieve
import com.algolia.search.saas.serialize.KeyIndexName
import com.algolia.search.saas.serialize.KeyObjectId
import indexA
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestRequestObjects : TestSerializer<RequestObjects>(RequestObjects) {

    private val objectID = ObjectId("objectA")

    override val items = listOf(
        RequestObjects(indexA, objectID) to json {
            KeyIndexName to indexA.raw
            KeyObjectId to objectID.raw
        },
        RequestObjects(indexA, objectID, attributeA, attributeB) to json {
            KeyIndexName to indexA.raw
            KeyObjectId to objectID.raw
            KeyAttributesToRetrieve to jsonArray {
                +attributeA.raw
                +attributeB.raw
            }
        }
    )
}