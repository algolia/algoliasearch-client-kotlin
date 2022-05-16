package documentation.guides.optimize.typo

import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideMultilingualSearch {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                searchableAttributes {
                    +"title_eng"
                    +"title_fr"
                    +"title_es"
                }
            }

            index.setSettings(settings)
        }
    }
}
