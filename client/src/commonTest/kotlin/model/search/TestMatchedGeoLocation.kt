package model.search

import com.algolia.search.helper.and
import com.algolia.search.model.search.MatchedGeoLocation
import com.algolia.search.serialize.internal.KeyDistance
import com.algolia.search.serialize.internal.KeyLat
import com.algolia.search.serialize.internal.KeyLng
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
            put(KeyDistance, 10)
            put(KeyLat, 1f)
            put(KeyLng, 2f)
        }
    }
}
