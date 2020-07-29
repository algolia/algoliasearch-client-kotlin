package serialize.serializer

import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.KSerializerObjectIDs
import com.algolia.search.serialize.KeyObjectID
import kotlinx.serialization.json.jsonArray
import objectIDA
import objectIDB
import serialize.TestSerializer

internal class TestKSerializerObjectIDs : TestSerializer<List<ObjectID>>(KSerializerObjectIDs) {

    override val items = listOf(
        listOf(objectIDA, objectIDB) to jsonArray {
            +json { KeyObjectID to objectIDA.raw }
            +json { KeyObjectID to objectIDB.raw }
        }
    )
}
