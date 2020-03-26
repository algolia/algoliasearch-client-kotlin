package serialize

import com.algolia.search.serialize.*
import shouldEqual
import kotlin.test.Test


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