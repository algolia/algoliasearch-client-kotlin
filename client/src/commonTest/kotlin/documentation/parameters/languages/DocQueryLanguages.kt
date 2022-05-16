package documentation.parameters.languages

import com.algolia.search.dsl.query
import com.algolia.search.dsl.queryLanguages
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.RemoveStopWords
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocQueryLanguages {

//    queryLanguages {
//        +QueryLanguage
//        +...
//    }

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                queryLanguages {
                    +Spanish
                }
                ignorePlurals = IgnorePlurals.True
                removeStopWords = RemoveStopWords.True
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                queryLanguages {
                    +Spanish
                    +Catalan
                }
            }

            index.search(query)
        }
    }

    @Test
    fun snippet3() {
        runTest {
            val query = query("query") {
                queryLanguages {
                    +Japanese
                    +English
                }
            }

            index.search(query)
        }
    }
}
