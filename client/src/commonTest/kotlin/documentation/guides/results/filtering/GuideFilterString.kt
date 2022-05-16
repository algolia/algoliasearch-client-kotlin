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
internal class GuideFilterString {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                attributesForFaceting {
                    +"brand" // or FilterOnly(brand) for filtering purposes only
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            // Only “Motorola” smartphones
            index.search(
                query("smartphone") {
                    filters {
                        and {
                            facet("brand", "Motorola")
                        }
                    }
                }
            )

            // Only “LG” or “Samsung” smartphones
            index.search(
                query("smartphone") {
                    filters {
                        orFacet {
                            facet("brand", "Motorola")
                            facet("brand", "Samsung")
                        }
                    }
                }
            )

            // Everything but “Apple” smartphones
            index.search(
                query("smartphone") {
                    filters {
                        and {
                            facet("brand", "Apple", isNegated = true)
                        }
                    }
                }
            )
        }
    }
}
