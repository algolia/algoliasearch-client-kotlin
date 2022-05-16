package documentation.guides.results.filtering

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.filters
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideFilterArray {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                attributesForFaceting {
                    +"categories" // or FilterOnly("categories") for filtering purposes only
                    +"store" // " FilterOnly("store") for filtering purposes only
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("harry") {
                filters {
                    and {
                        facet("categories", "politics")
                        facet("store", "Gibert Joseph Saint-Michel")
                    }
                }
            }

            index.search(query)
        }
    }
}
