package documentation.parameters.strategy

import com.algolia.search.dsl.optionalWords
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocOptionalWords {

//    optionalWords {
//        +"word1"
//        +"word2 word3" // both words are optional
//    }

    @Test
    fun snippet1() {
        runBlocking {
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
        runBlocking {
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
        runBlocking {
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
