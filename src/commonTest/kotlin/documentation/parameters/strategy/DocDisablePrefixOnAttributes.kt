package documentation.parameters.strategy

import com.algolia.search.dsl.disablePrefixOnAttributes
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocDisablePrefixOnAttributes {

//    disablePrefixOnAttributes {
//        +"attribute"
//        +...
//    }

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                disablePrefixOnAttributes { +"sku" }
            }

            index.setSettings(settings)
        }
    }
}
