package documentation.parameters.languages

import com.algolia.search.dsl.query
import com.algolia.search.dsl.queryLanguages
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.RemoveStopWords
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocQueryLanguages : TestDocumentation() {

//    queryLanguages {
//        +QueryLanguage
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                queryLanguages {
                    +Spanish
                }
                ignorePlurals = IgnorePlurals.Boolean(true)
                removeStopWords = RemoveStopWords.Boolean(true)
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
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
    fun queryDetection() {
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