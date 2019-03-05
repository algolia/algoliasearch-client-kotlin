package model.search

import com.algolia.search.helper.and
import com.algolia.search.model.search.MatchedGeoLocation
import com.algolia.search.serialize.KeyDistance
import com.algolia.search.serialize.KeyLat
import com.algolia.search.serialize.KeyLng
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer


@RunWith(JUnit4::class)
internal class TestMatchedGeoLocation: TestSerializer<MatchedGeoLocation>(MatchedGeoLocation) {

    override val items = listOf(
        item to json
    )

    companion object {

        val item = MatchedGeoLocation(
            point = 1f and 2f,
            distance = 10
        )
        val json = json {
            KeyDistance to 10
            KeyLat to 1f
            KeyLng to 2f
        }
    }
}