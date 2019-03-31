package documentation.parameters.languages

import com.algolia.search.dsl.query
import com.algolia.search.dsl.queryLanguages
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.IgnorePlurals
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocLanguages : TestDocumentation() {

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                ignorePlurals = IgnorePlurals.Boolean(true)
                queryLanguages {
                    +Spanish
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query {
                ignorePlurals = IgnorePlurals.Boolean(true)
                queryLanguages {
                    +Spanish
                    +Catalan
                }
            }

            index.search(query)
        }
    }
}