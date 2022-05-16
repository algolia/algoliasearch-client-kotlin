package documentation.parameters.strategy

import com.algolia.search.dsl.optionalWords
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocOptionalWords {

//    optionalWords {
//        +"word1"
//        +"word2 word3" // both words are optional
//    }

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                optionalWords {
                    +"word1"
                    +"word2 word3"
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                optionalWords {
                    +"word1"
                    +"word2 word3"
                }
            }

            index.search(query)
        }
    }

    @Test
    fun snippet3() {
        runTest {
            val queryString = "query"
            val query = query(queryString) {
                optionalWords {
                    +queryString
                }
            }

            index.search(query)
        }
    }
}
