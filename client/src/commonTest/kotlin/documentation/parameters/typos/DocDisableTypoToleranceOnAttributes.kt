package documentation.parameters.typos

import com.algolia.search.dsl.disableTypoToleranceOnAttributes
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocDisableTypoToleranceOnAttributes {

//    disableTypoToleranceOnAttributes {
//        +"attribute"
//        +...
//    }

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                disableTypoToleranceOnAttributes {
                    +"sku"
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                disableTypoToleranceOnAttributes {
                    +"sku"
                }
            }

            index.search(query)
        }
    }
}
