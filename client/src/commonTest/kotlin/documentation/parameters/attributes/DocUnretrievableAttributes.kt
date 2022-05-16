package documentation.parameters.attributes

import com.algolia.search.dsl.settings
import com.algolia.search.dsl.unretrieveableAttributes
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocUnretrievableAttributes {

//    unretrieveableAttributes {
//        +"attribute"
//        +...
//    }

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                unretrieveableAttributes {
                    +"total_number_of_sales"
                }
            }

            index.setSettings(settings)
        }
    }
}
