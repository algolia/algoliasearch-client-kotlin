package documentation.parameters.languages

import com.algolia.search.dsl.query
import com.algolia.search.dsl.queryLanguages
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.QueryLanguage
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocIgnorePlurals {

//    ignorePlurals: IgnorePlurals = IgnorePlurals.Boolean|IgnorePlurals.QueryLanguages

    @Test
    fun snippet1() {
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
    fun snippet2() {
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