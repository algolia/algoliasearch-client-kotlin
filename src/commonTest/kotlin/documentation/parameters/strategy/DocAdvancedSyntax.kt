package documentation.parameters.strategy

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocAdvancedSyntax {

//    advancedSyntax: Boolean = true|false

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                advancedSyntax = true
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                advancedSyntax = true
            }

            index.search(query)
        }
    }
}
