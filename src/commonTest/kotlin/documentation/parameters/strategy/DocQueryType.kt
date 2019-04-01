package documentation.parameters.strategy

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.QueryType
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocQueryType : TestDocumentation() {

//    queryType: QueryType = [QueryType.PrefixLast](#parameter-option-prefixlast)
//    | [QueryType.PrefixAll](#parameter-option-prefixall)
//    | [QueryType.PrefixNone](#parameter-option-prefixnone)

    @Test
    fun settings() {
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
    fun query() {
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