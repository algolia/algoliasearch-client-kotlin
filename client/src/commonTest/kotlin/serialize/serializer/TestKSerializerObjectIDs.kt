package serialize.serializer

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.KSerializerObjectIDs
import com.algolia.search.serialize.internal.KeyObjectID
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import objectIDA
import objectIDB
import serialize.TestSerializer

internal class TestKSerializerObjectIDs : TestSerializer<List<ObjectID>>(KSerializerObjectIDs) {

    override val items = listOf(
        listOf(objectIDA, objectIDB) to buildJsonArray {
            add(buildJsonObject { put(KeyObjectID, objectIDA.raw) })
            add(buildJsonObject { put(KeyObjectID, objectIDB.raw) })
        }
    )
}
