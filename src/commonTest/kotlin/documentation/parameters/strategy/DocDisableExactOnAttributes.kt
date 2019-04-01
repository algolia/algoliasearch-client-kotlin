package documentation.parameters.strategy

import com.algolia.search.dsl.disableExactOnAttributes
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocDisableExactOnAttributes {

//    disableExactOnAttributes {
//        +"attribute"
//        +...
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                disableExactOnAttributes { +"keywords" }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                disableExactOnAttributes { +"keywords" }
            }

            index.search(query)
        }
    }
}