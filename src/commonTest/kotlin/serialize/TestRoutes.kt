package serialize

import com.algolia.search.serialize.RouteABTestsV2
import com.algolia.search.serialize.RouteClustersV1
import com.algolia.search.serialize.RouteEventsV1
import com.algolia.search.serialize.RouteIndexesV1
import com.algolia.search.serialize.RouteKeysV1
import com.algolia.search.serialize.RouteLogs
import com.algolia.search.serialize.RouteRules
import com.algolia.search.serialize.RouteSettings
import com.algolia.search.serialize.RouteSynonyms
import kotlin.test.Test
import shouldEqual

internal class TestRoutes {

    @Test
    fun test() {
        RouteIndexesV1 shouldEqual "1/indexes"
        RouteSettings shouldEqual "settings"
        RouteClustersV1 shouldEqual "1/clusters"
        RouteSynonyms shouldEqual "synonyms"
        RouteEventsV1 shouldEqual "1/events"
        RouteABTestsV2 shouldEqual "2/abtests"
        RouteRules shouldEqual "rules"
        RouteKeysV1 shouldEqual "1/keys"
        RouteLogs shouldEqual "1/logs"
    }
}
