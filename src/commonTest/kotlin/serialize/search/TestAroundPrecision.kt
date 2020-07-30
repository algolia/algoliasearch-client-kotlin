package serialize.search

import com.algolia.search.model.search.AroundPrecision
import com.algolia.search.serialize.KeyFrom
import com.algolia.search.serialize.KeyValue
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestAroundPrecision : TestSerializer<AroundPrecision>(AroundPrecision) {

    override val items = listOf(
        AroundPrecision.Int(0) to JsonPrimitive(0),
        AroundPrecision.Ranges(0 until 10) to buildJsonArray {
            add(buildJsonObject {
                put(KeyFrom, 0)
                put(KeyValue, 9)
            })
        }
    )
}
