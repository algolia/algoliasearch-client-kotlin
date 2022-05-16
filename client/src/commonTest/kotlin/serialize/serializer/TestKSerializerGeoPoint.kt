package serialize.serializer

import com.algolia.search.model.search.Point
import com.algolia.search.serialize.KSerializerGeoPoint
import com.algolia.search.serialize.internal.KeyLat
import com.algolia.search.serialize.internal.KeyLng
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestKSerializerGeoPoint : TestSerializer<Point>(KSerializerGeoPoint) {

    override val items = listOf(point to jsonObject)

    companion object {

        val jsonObject = buildJsonObject {
            put(KeyLat, 0f)
            put(KeyLng, 1f)
        }
        val point = Point(latitude = 0f, longitude = 1f)
    }
}
