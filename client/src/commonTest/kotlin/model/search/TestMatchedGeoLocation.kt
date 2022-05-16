package model.search

import com.algolia.search.helper.and
import com.algolia.search.model.search.MatchedGeoLocation
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestMatchedGeoLocation : TestSerializer<MatchedGeoLocation>(MatchedGeoLocation) {

    override val items: List<Pair<MatchedGeoLocation, JsonElement>> = listOf(item to jsonObject)

    companion object {

        val item = MatchedGeoLocation(
            point = 1f and 2f,
            distance = 10
        )
        val jsonObject = buildJsonObject {
            put(Key.Distance, 10)
            put(Key.Lat, 1f)
            put(Key.Lng, 2f)
        }
    }
}
