package documentation.parameters.strategy

import com.algolia.search.dsl.disablePrefixOnAttributes
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocDisablePrefixOnAttributes : TestDocumentation() {

//    disablePrefixOnAttributes {
//        +"attribute"
//        +...
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                disablePrefixOnAttributes { +"sku" }
            }

            index.setSettings(settings)
        }
    }
}