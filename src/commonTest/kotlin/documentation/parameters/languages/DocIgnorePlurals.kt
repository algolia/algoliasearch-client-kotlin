package documentation.parameters.languages

import com.algolia.search.dsl.query
import com.algolia.search.dsl.queryLanguages
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.QueryLanguage
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocIgnorePlurals : TestDocumentation() {

//    ignorePlurals: IgnorePlurals = IgnorePlurals.Boolean|IgnorePlurals.QueryLanguages

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                queryLanguages {
                    +Spanish
                }
                ignorePlurals = IgnorePlurals.Boolean(true)
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                ignorePlurals = IgnorePlurals.QueryLanguages(
                    QueryLanguage.Spanish,
                    QueryLanguage.Catalan
                )
            }

            index.search(query)
        }
    }
}