package serialize.serializer

import com.algolia.search.model.search.Point
import com.algolia.search.serialize.KSerializerGeoPoint
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestKSerializerGeoPoint : TestSerializer<Point>(KSerializerGeoPoint) {

    override val items = listOf(point to jsonObject)

    companion object {

        val jsonObject = buildJsonObject {
            put(Key.Lat, 0f)
            put(Key.Lng, 1f)
        }
        val point = Point(latitude = 0f, longitude = 1f)
    }
}
