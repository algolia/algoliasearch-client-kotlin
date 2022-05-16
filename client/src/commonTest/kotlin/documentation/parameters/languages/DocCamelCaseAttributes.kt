package documentation.parameters.languages

import com.algolia.search.dsl.camelCaseAttributes
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocCamelCaseAttributes {

//    camelCaseAttributes {
//        +"attribute"
//        +...
//    }

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                camelCaseAttributes { +"description" }
            }

            index.setSettings(settings)
        }
    }
}
