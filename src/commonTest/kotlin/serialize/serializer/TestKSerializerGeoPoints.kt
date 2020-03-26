package serialize.serializer

import com.algolia.search.model.search.Point
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.KSerializerGeoPoints
import kotlin.test.Test
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer
import serialize.serializer.TestKSerializerGeoPoint.Companion.json
import serialize.serializer.TestKSerializerGeoPoint.Companion.point
import shouldEqual

internal class TestKSerializerGeoPoints : TestSerializer<List<Point>>(KSerializerGeoPoints) {

    override val items = listOf(
        listOf(point) to jsonArray { +json }
    )

    @Test
    fun fromObject() {
        Json.fromJson(KSerializerGeoPoints, json) shouldEqual listOf((point))
    }
}
