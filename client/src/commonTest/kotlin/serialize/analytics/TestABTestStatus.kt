package serialize.analytics

import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer

internal class TestABTestStatus : TestSerializer<ABTestStatus>(ABTestStatus) {

    override val items = listOf(
        ABTestStatus.Active to JsonPrimitive(Key.Active),
        ABTestStatus.Stopped to JsonPrimitive(Key.Stopped),
        ABTestStatus.Expired to JsonPrimitive(Key.Expired),
        ABTestStatus.Failed to JsonPrimitive(Key.Failed)
    )
}
