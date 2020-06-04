package serialize.serializer

import com.algolia.search.model.search.Facet
import com.algolia.search.serialize.KSerializerFacetList
import com.algolia.search.serialize.KeyCount
import com.algolia.search.serialize.KeyValue
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer
import unknown

internal class TestKSerializerFacetList : TestSerializer<List<Facet>>(KSerializerFacetList) {

    override val items = listOf(
        listOf(Facet(unknown, 0)) to jsonArray {
            +json {
                KeyValue to unknown
                KeyCount to 0
            }
        }
    )
}
