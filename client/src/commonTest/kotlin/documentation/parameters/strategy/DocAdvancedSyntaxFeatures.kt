package documentation.parameters.strategy

import com.algolia.search.dsl.advancedSyntaxFeatures
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocAdvancedSyntaxFeatures {

//    advancedSyntaxFeatures {
//        +ExactPhrase
//        +ExcludeWords
//    }

    @Test
    fun snippet1() {
        runTest {
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
        runTest {
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
        runTest {
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
