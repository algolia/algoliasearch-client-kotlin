package documentation.parameters.strategy

import com.algolia.search.dsl.disableExactOnAttributes
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocDisableExactOnAttributes {

//    disableExactOnAttributes {
//        +"attribute"
//        +...
//    }

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                disableExactOnAttributes { +"keywords" }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                disableExactOnAttributes { +"keywords" }
            }

            index.search(query)
        }
    }
}
