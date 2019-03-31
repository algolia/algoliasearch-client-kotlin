package documentation.parameters.typos

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocMinWordSizeFor1Typo : TestDocumentation() {

//    minWordSizeFor1Typo: Int = min_word_size

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                minWordSizeFor1Typo = 4
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                minWordSizeFor1Typo = 2
            }

            index.search(query)
        }
    }
}