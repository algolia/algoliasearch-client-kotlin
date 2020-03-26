package documentation.parameters.languages

import com.algolia.search.dsl.query
import com.algolia.search.dsl.queryLanguages
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Language
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

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
                ignorePlurals = IgnorePlurals.True
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                ignorePlurals = IgnorePlurals.QueryLanguages(
                    Language.Spanish,
                    Language.Catalan
                )
            }

            index.search(query)
        }
    }
}
