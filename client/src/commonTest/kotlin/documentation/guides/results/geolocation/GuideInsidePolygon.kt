package documentation.guides.results.geolocation

import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.insidePolygon
import com.algolia.search.dsl.query
import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.Polygon
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideInsidePolygon {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                searchableAttributes {
                    +"name"
                    +"city"
                    +"country"
                    +"iata_code"
                }
                customRanking {
                    +Desc("links_count")
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query {
                insidePolygon {
                    +Polygon(
                        Point(42.01f, -124.31f),
                        Point(48.835509470063045f, -124.40453125000005f),
                        Point(45.01082951668149f, -65.95726562500005f),
                        Point(31.247243545293433f, -81.06578125000004f),
                        Point(25.924152577235226f, -97.68234374999997f),
                        Point(32.300311895879545f, -117.54828125f)
                    )
                }
            }

            index.search(query)
        }
    }
}
