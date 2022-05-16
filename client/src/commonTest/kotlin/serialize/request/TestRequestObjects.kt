package serialize.request

import attributes
import attributesJson
import com.algolia.search.model.ObjectID
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.serialize.internal.KeyAttributesToRetrieve
import com.algolia.search.serialize.internal.KeyIndexName
import com.algolia.search.serialize.internal.KeyObjectID
import com.algolia.search.serialize.internal.JsonNoDefaults
import indexA
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestRequestObjects : TestSerializer<RequestObjects>(RequestObjects.serializer(), JsonNoDefaults) {

    private val objectID = ObjectID("objectA")

    override val items = listOf(
        RequestObjects(indexA, objectID) to buildJsonObject {
            put(KeyIndexName, indexA.raw)
            put(KeyObjectID, objectID.raw)
        },
        RequestObjects(indexA, objectID, attributes) to buildJsonObject {
            put(KeyIndexName, indexA.raw)
            put(KeyObjectID, objectID.raw)
            put(KeyAttributesToRetrieve, attributesJson)
        }
    )
}
