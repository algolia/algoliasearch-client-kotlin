package documentation.parameters.typos

import com.algolia.search.dsl.disableTypoToleranceOnAttributes
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
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
        runBlocking {
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
        runBlocking {
            val query = query("query") {
                disableTypoToleranceOnAttributes {
                    +"sku"
                }
            }

            index.search(query)
        }
    }
}
