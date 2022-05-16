package documentation.parameters.typos

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocMinWordSizeFor2Typos {

//    minWordSizeFor2Typo: Int = min_word_size

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                minWordSizeFor2Typos = 4
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                minWordSizeFor2Typos = 2
            }

            index.search(query)
        }
    }
}
