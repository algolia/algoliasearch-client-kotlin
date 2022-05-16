package documentation.parameters.languages

import com.algolia.search.dsl.query
import com.algolia.search.dsl.queryLanguages
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Language
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocIgnorePlurals {

//    ignorePlurals: IgnorePlurals = IgnorePlurals.Boolean|IgnorePlurals.QueryLanguages

    @Test
    fun snippet1() {
        runTest {
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
        runTest {
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
