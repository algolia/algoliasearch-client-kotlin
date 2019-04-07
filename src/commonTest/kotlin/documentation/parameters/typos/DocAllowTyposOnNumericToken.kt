package documentation.parameters.typos

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocAllowTyposOnNumericToken {

//    allowTyposOnNumericTokens: Boolean = true|false

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                allowTyposOnNumericTokens = false
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                allowTyposOnNumericTokens = false
            }

            index.search(query)
        }
    }
}