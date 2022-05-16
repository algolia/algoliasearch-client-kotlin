package serialize

import com.algolia.search.serialize.internal.Route
import shouldEqual
import kotlin.test.Test

internal class TestRoutes {

    @Test
    fun test() {
        Route.IndexesV1 shouldEqual "1/indexes"
        Route.Settings shouldEqual "settings"
        Route.ClustersV1 shouldEqual "1/clusters"
        Route.Synonyms shouldEqual "synonyms"
        Route.EventsV1 shouldEqual "1/events"
        Route.ABTestsV2 shouldEqual "2/abtests"
        Route.Rules shouldEqual "rules"
        Route.KeysV1 shouldEqual "1/keys"
        Route.Logs shouldEqual "1/logs"
    }
}
