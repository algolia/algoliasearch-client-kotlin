package documentation.parameters.strategy

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.QueryType
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocQueryType {

//    queryType: QueryType = [QueryType.PrefixLast](#parameter-option-prefixlast)
//    | [QueryType.PrefixAll](#parameter-option-prefixall)
//    | [QueryType.PrefixNone](#parameter-option-prefixnone)

    @Test
    fun snippet1() {
        runBlocking {
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
        runBlocking {
            val query = query("query") {
                queryType = QueryType.PrefixLast
                // queryType = QueryType.PrefixAll
                // queryType = QueryType.PrefixNone
            }

            index.search(query)
        }
    }
}