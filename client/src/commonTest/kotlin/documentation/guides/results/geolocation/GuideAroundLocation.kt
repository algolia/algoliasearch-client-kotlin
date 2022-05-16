package documentation.guides.results.geolocation

import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.query
import com.algolia.search.dsl.ranking
import com.algolia.search.dsl.requestOptions
import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.Point
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideAroundLocation {

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
                ranking {
                    +Typo
                    +Geo
                    +Words
                    +Attribute
                    +Proximity
                    +Exact
                    +Custom
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query {
                aroundLatLng = Point(40.71f, -74.01f)
            }

            index.search(query)
        }
    }

    @Test
    fun snippet3() {
        runTest {
            val query = query {
                aroundLatLngViaIP = true
            }

            index.search(query, requestOptions { headerForwardedFor("94.228.178.246") })
        }
    }

    @Test
    fun snippet4() {
        runTest {
            val query = query {
                aroundLatLng = Point(40.71f, -74.01f)
                aroundRadius = AroundRadius.InMeters(10000000) // 10000km
            }

            index.search(query)
        }
    }
}
