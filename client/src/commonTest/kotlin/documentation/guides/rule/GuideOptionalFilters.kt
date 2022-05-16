package documentation.guides.rule

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.optionalFilters
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore

@Ignore
class GuideOptionalFilters {

    /** Optional filter **/
    fun snippet1() {
        runTest {
            val query = query("phone") {
                optionalFilters {
                    and {
                        facet("brand", "Apple", score = 3)
                        facet("brand", "Samsung", score = 2)
                        facet("brand", "-Huawei")
                    }
                }
            }
            index.search(query = query)
        }
    }

    /** Filter score **/
    fun snippet2() {
        runTest {
            val query = query {
                optionalFilters {
                    and {
                        facet("brand", "Apple", score = 2)
                        facet("type", "tablet")
                    }
                }
            }
            index.search(query = query)
        }
    }

    /** Filter only faceting **/
    fun snippets3() {
        runTest {
            val settings = settings {
                attributesForFaceting {
                    +"filterOnly(brand)"
                }
            }
            index.setSettings(settings)
        }
    }
}
