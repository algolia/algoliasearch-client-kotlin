package documentation.parameters.typos

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocMinWordSizeFor2Typos {

//    minWordSizeFor2Typo: Int = min_word_size

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                minWordSizeFor2Typos = 4
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                minWordSizeFor2Typos = 2
            }

            index.search(query)
        }
    }
}
