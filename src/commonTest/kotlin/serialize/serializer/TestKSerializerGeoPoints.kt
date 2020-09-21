package serialize.serializer

import com.algolia.search.model.search.Point
import com.algolia.search.serialize.KSerializerGeoPoints
import com.algolia.search.serialize.internal.Json
import kotlinx.serialization.json.buildJsonArray
import serialize.TestSerializer
import serialize.serializer.TestKSerializerGeoPoint.Companion.jsonObject
import serialize.serializer.TestKSerializerGeoPoint.Companion.point
import shouldEqual
import kotlin.test.Test

internal class TestKSerializerGeoPoints : TestSerializer<List<Point>>(KSerializerGeoPoints) {

    override val items = listOf(
        listOf(point) to buildJsonArray { add(jsonObject) }
    )

    @Test
    fun fromObject() {
        Json.decodeFromJsonElement(KSerializerGeoPoints, jsonObject) shouldEqual listOf((point))
    }
}
