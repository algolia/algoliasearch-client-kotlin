package documentation.guides.results.geolocation

import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.insideBoundingBox
import com.algolia.search.dsl.query
import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.BoundingBox
import com.algolia.search.model.search.Point
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class GuideInsideRectangle {

    @Test
    fun snippet1() {
        runBlocking {
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
        runBlocking {
            val query = query {
                insideBoundingBox {
                    +BoundingBox(
                        Point(49.067996905313834f, 65.73828125f),
                        Point(25.905859247243498f, 128.8046875f)
                    )
                }
            }

            index.search(query)
        }
    }
}
