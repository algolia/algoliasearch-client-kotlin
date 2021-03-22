package documentation.guides.optimize.typo

import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideMultilingualSearch {

    @Test
    fun snippet1() {
        runBlocking {
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
