package documentation.parameters.strategy

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocAdvancedSyntax {

//    advancedSyntax: Boolean = true|false

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                advancedSyntax = true
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                advancedSyntax = true
            }

            index.search(query)
        }
    }
}
