package serialize.request

import attributes
import attributesJson
import com.algolia.search.model.ObjectID
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.serialize.KeyAttributesToRetrieve
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.noDefaults
import indexA
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer


@RunWith(JUnit4::class)
internal class TestRequestObjects : TestSerializer<RequestObjects>(RequestObjects.serializer(), Json.noDefaults) {

    private val objectID = ObjectID("objectA")

    override val items = listOf(
        RequestObjects(indexA, objectID) to json {
            KeyIndexName to indexA.raw
            KeyObjectID to objectID.raw
        },
        RequestObjects(indexA, objectID, attributes) to json {
            KeyIndexName to indexA.raw
            KeyObjectID to objectID.raw
            KeyAttributesToRetrieve to attributesJson
        }
    )
}