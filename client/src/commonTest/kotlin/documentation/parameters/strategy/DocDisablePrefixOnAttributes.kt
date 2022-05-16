package documentation.parameters.strategy

import com.algolia.search.dsl.disablePrefixOnAttributes
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocDisablePrefixOnAttributes {

//    disablePrefixOnAttributes {
//        +"attribute"
//        +...
//    }

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                disablePrefixOnAttributes { +"sku" }
            }

            index.setSettings(settings)
        }
    }
}
