package serialize.serializer

import com.algolia.search.model.search.Point
import com.algolia.search.serialize.KSerializerGeoPoint
import com.algolia.search.serialize.KeyLat
import com.algolia.search.serialize.KeyLng
import serialize.TestSerializer

internal class TestKSerializerGeoPoint : TestSerializer<Point>(KSerializerGeoPoint) {

    override val items = listOf(
        point to json
    )

    companion object {

        val json = json {
            KeyLat to 0f
            KeyLng to 1f
        }
        val point = Point(latitude = 0f, longitude = 1f)
    }
}
