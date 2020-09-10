package documentation.guides.rule

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.optionalFilters
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore

@Ignore
class GuideOptionalFilters {

    /** Optional filter **/
    fun snippet1() {
        runBlocking {
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
        runBlocking {
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
        runBlocking {
            val settings = settings {
                attributesForFaceting {
                    +"filterOnly(brand)"
                }
            }
            index.setSettings(settings)
        }
    }
}
