package documentation.parameters.languages

import com.algolia.search.dsl.query
import com.algolia.search.dsl.queryLanguages
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.RemoveStopWords
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocQueryLanguages {

//    queryLanguages {
//        +QueryLanguage
//        +...
//    }

    @Test
    fun snippet1() {
        runBlocking {
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
        runBlocking {
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
        runBlocking {
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
