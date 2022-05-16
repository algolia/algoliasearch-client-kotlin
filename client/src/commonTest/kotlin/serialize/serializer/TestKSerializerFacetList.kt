package serialize.serializer

import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.KSerializerFacetList
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import unknown

internal class TestKSerializerFacetList : TestSerializer<List<Facet>>(KSerializerFacetList) {

    override val items = listOf(
        listOf(Facet(unknown, 0)) to buildJsonArray {
            add(
                buildJsonObject {
                    put(Key.Value, unknown)
                    put(Key.Count, 0)
                }
            )
        }
    )
}
