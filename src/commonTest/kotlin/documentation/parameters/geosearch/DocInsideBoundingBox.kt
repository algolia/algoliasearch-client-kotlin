package documentation.parameters.geosearch

import com.algolia.search.dsl.insideBoundingBox
import com.algolia.search.dsl.query
import com.algolia.search.model.search.BoundingBox
import com.algolia.search.model.search.Point
import documentation.index
import runBlocking
import kotlin.test.Test


internal class DocInsideBoundingBox {

//    insideBoundingBox {
//        +BoundingBox(
//            Point(latitude, longitude),
//            Point(latitude, longitude)
//        )
//    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                insideBoundingBox {
                    +BoundingBox(
                        Point(46.650828100116044f, 7.123046875f),
                        Point(45.17210966999772f, 1.009765625f)
                    )
                }
            }

            index.search(query)
        }
    }

    @Test
    fun queryMultiple() {
        runBlocking {
            val query = query("query") {
                insideBoundingBox {
                    +BoundingBox(
                        Point(latitude = 46.650828100116044f, longitude = 7.123046875f),
                        Point(latitude = 45.17210966999772f, longitude = 1.009765625f)
                    )
                    +BoundingBox(
                        Point(latitude = 49.62625916704081f, longitude = 4.6181640625f),
                        Point(latitude = 47.715070300900194f, longitude = 0.482421875f)
                    )
                }
            }

            index.search(query)
        }
    }
}