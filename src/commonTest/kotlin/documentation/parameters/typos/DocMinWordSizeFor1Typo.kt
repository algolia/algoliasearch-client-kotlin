package documentation.parameters.typos

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocMinWordSizeFor1Typo {

//    minWordSizeFor1Typo: Int = min_word_size

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                minWordSizeFor1Typo = 4
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                minWordSizeFor1Typo = 2
            }

            index.search(query)
        }
    }
}