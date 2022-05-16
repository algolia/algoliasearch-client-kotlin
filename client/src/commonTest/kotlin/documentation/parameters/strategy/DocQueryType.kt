package documentation.parameters.strategy

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.QueryType
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocQueryType {

//    queryType: QueryType = [QueryType.PrefixLast](#parameter-option-prefixlast)
//    | [QueryType.PrefixAll](#parameter-option-prefixall)
//    | [QueryType.PrefixNone](#parameter-option-prefixnone)

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                queryType = QueryType.PrefixLast
                // queryType = QueryType.PrefixAll
                // queryType = QueryType.PrefixNone
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                queryType = QueryType.PrefixLast
                // queryType = QueryType.PrefixAll
                // queryType = QueryType.PrefixNone
            }

            index.search(query)
        }
    }
}
