package serialize

import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.serialize.KeyActive
import com.algolia.search.serialize.KeyExpired
import com.algolia.search.serialize.KeyFailed
import com.algolia.search.serialize.KeyStopped
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestABTestStatus : TestSerializer<ABTestStatus>(ABTestStatus) {

    override val items = listOf(
        ABTestStatus.Active to JsonLiteral(KeyActive),
        ABTestStatus.Stopped to JsonLiteral(KeyStopped),
        ABTestStatus.Expired to JsonLiteral(KeyExpired),
        ABTestStatus.Failed to JsonLiteral(KeyFailed)
    )
}