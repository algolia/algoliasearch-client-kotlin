package documentation.guides.results.geolocation

import com.algolia.search.dsl.*
import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.Point
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideAroundLocation {

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
        runBlocking {
            val query = query {
                aroundLatLng = Point(40.71f, -74.01f)
            }

            index.search(query)
        }
    }

    @Test
    fun snippet3() {
        runBlocking {
            val query = query {
                aroundLatLngViaIP = true
            }

            index.search(query, requestOptions { headerForwardedFor("94.228.178.246") })
        }
    }

    @Test
    fun snippet4() {
        runBlocking {
            val query = query {
                aroundLatLng = Point(40.71f, -74.01f)
                aroundRadius = AroundRadius.InMeters(10000000) // 10000km
            }

            index.search(query)
        }
    }
}