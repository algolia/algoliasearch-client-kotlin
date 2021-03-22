package serialize.analytics

import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.serialize.KeyActive
import com.algolia.search.serialize.KeyExpired
import com.algolia.search.serialize.KeyFailed
import com.algolia.search.serialize.KeyStopped
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer

internal class TestABTestStatus : TestSerializer<ABTestStatus>(ABTestStatus) {

    override val items = listOf(
        ABTestStatus.Active to JsonPrimitive(KeyActive),
        ABTestStatus.Stopped to JsonPrimitive(KeyStopped),
        ABTestStatus.Expired to JsonPrimitive(KeyExpired),
        ABTestStatus.Failed to JsonPrimitive(KeyFailed)
    )
}
