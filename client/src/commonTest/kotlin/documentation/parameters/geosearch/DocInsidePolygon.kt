package documentation.parameters.geosearch

import com.algolia.search.dsl.insidePolygon
import com.algolia.search.dsl.query
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.Polygon
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocInsidePolygon {

//    insidePolygon {
//        +Polygon(
//            [Point(46.650828100116044f, 7.123046875f),
//            Point(45.17210966999772f, 1.009765625f),
//            Point(49.62625916704081f, 4.6181640625f),
//            ...](#parameter-option-a-polyhgon)
//        )
//        +... // you can search in [multiple polygon](#parameter-option-multiple-polygons)
//    }

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                insidePolygon {
                    +Polygon(
                        Point(latitude = 46.650828100116044f, longitude = 7.123046875f),
                        Point(latitude = 45.17210966999772f, longitude = 1.009765625f),
                        Point(latitude = 49.62625916704081f, longitude = 4.6181640625f)
                    )
                }
            }

            index.search(query)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                insidePolygon {
                    +Polygon(
                        Point(latitude = 46.650828100116044f, longitude = 7.123046875f),
                        Point(latitude = 45.17210966999772f, longitude = 1.009765625f),
                        Point(latitude = 49.62625916704081f, longitude = 4.6181640625f)
                    )
                    +Polygon(
                        Point(latitude = 49.62625916704081f, longitude = 4.6181640625f),
                        Point(latitude = 47.715070300900194f, longitude = 0.482421875f),
                        Point(latitude = 45.17210966999772f, longitude = 1.009765625f),
                        Point(latitude = 50.62626704081f, longitude = 4.6181640625f)
                    )
                }
            }

            index.search(query)
        }
    }
}
