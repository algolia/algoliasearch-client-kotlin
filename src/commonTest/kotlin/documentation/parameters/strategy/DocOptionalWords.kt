package documentation.parameters.strategy

import com.algolia.search.dsl.optionalWords
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocOptionalWords : TestDocumentation() {

//    optionalWords {
//        +"word1"
//        +"word2 word3" // both words are optional
//    }

    @Test
    fun settings() {
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
    fun query() {
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
    fun allOptionals() {
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