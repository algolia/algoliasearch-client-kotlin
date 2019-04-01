package documentation.parameters.rule

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocEnableRules {

//    enableRules: Boolean = true|false

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                enableRules = true
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                enableRules = true
            }

            index.search(query)
        }
    }
}