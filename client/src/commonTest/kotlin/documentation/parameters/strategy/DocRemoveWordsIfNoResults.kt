package documentation.parameters.strategy

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.RemoveWordIfNoResults
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocRemoveWordsIfNoResults {

//    removeWordsIfNoResults: RemoveWordsIfNoResults = [RemoveWordsIfNoResults.None](#parameter-option-none)
//    |  [RemoveWordsIfNoResults.LastWords](#parameter-option-lastwords)
//    |  [RemoveWordsIfNoResults.FirstWords](#parameter-option-firstwords)
//    |  [RemoveWordsIfNoResults.AllOptional](#parameter-option-alloptional)

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                removeWordsIfNoResults = RemoveWordIfNoResults.None
                // removeWordsIfNoResults = RemoveWordIfNoResults.LastWords
                // removeWordsIfNoResults = RemoveWordIfNoResults.FirstWords
                // removeWordsIfNoResults = RemoveWordIfNoResults.AllOptional
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                removeWordsIfNoResults = RemoveWordIfNoResults.LastWords
                // removeWordsIfNoResults = RemoveWordIfNoResults.None
                // removeWordsIfNoResults = RemoveWordIfNoResults.FirstWords
                // removeWordsIfNoResults = RemoveWordIfNoResults.AllOptional
            }

            index.search(query)
        }
    }
}
