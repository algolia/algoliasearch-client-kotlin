package documentation.parameters.strategy

import com.algolia.search.dsl.alternativesAsExact
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocAlternativesAsExact {

//    alternativesAsExact {
//        +[AlternativesAsExact.IgnorePlurals](#parameter-option-ignoreplurals)
//        +[AlternativesAsExact.SingleWordSynonym](#parameter-option-singlewordsynonym)
//        +[AlternativesAsExact.MultiWordsSynonym](#parameter-option-multiwordssynonym)
//    }

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                alternativesAsExact {
                    +IgnorePlurals
                    +SingleWordSynonym
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                alternativesAsExact {
                    +MultiWordsSynonym
                }
            }

            index.search(query)
        }
    }
}
