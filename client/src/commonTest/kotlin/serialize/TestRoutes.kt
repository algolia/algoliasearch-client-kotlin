package serialize

import com.algolia.search.serialize.internal.RouteABTestsV2
import com.algolia.search.serialize.internal.RouteClustersV1
import com.algolia.search.serialize.internal.RouteEventsV1
import com.algolia.search.serialize.internal.RouteIndexesV1
import com.algolia.search.serialize.internal.RouteKeysV1
import com.algolia.search.serialize.internal.RouteLogs
import com.algolia.search.serialize.internal.RouteRules
import com.algolia.search.serialize.internal.RouteSettings
import com.algolia.search.serialize.internal.RouteSynonyms
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
