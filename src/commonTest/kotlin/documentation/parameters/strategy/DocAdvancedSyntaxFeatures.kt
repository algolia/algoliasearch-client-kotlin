package documentation.parameters.strategy

import com.algolia.search.dsl.advancedSyntaxFeatures
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocAdvancedSyntaxFeatures {

//    advancedSyntaxFeatures {
//        +ExactPhrase
//        +ExcludeWords
//    }

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                advancedSyntaxFeatures {
                    +ExactPhrase
                    +ExcludeWords
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                advancedSyntax = true
                advancedSyntaxFeatures {
                    +ExactPhrase
                }
            }

            index.search(query)
        }
    }

    @Test
    fun snippet3() {
        runBlocking {
            val query = query("query") {
                advancedSyntax = true
                advancedSyntaxFeatures {
                    +ExcludeWords
                }
            }

            index.search(query)
        }
    }
}
