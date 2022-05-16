package documentation.parameters.languages

import com.algolia.search.dsl.decompoundedAttributes
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocDecompoundedAttributes {

//    decompoundedAttributes {
//        +language { +"attribute" }
//        +...
//    }

    @Test
    fun snippet2() {
        runTest {
            val settings = settings {
                decompoundedAttributes {
                    german { +"name" }
                }
            }

            index.setSettings(settings)
        }
    }
}
