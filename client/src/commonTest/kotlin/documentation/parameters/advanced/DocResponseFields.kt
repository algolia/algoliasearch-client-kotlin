package documentation.parameters.advanced

import com.algolia.search.dsl.query
import com.algolia.search.dsl.responseFields
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocResponseFields {

//    responseFields {
//        +ResponseFields
//        +...
//    }

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                responseFields {
                    +Hits
                    +HitsPerPage
                    +NbPages
                    +Page
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                responseFields {
                    +Hits
                    +Facets
                }
            }

            index.search(query)
        }
    }
}
