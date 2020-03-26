package serialize.search

import com.algolia.search.model.search.AroundPrecision
import com.algolia.search.serialize.KeyFrom
import com.algolia.search.serialize.KeyValue
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer


internal class TestAroundPrecision : TestSerializer<AroundPrecision>(AroundPrecision) {

    override val items = listOf(
        AroundPrecision.Int(0) to JsonLiteral(0),
        AroundPrecision.Ranges(0 until 10) to jsonArray {
            +json {
                KeyFrom to 0
                KeyValue to 9
            }
        }
    )
}