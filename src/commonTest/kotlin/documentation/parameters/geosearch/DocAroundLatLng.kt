package documentation.parameters.geosearch

import com.algolia.search.dsl.query
import com.algolia.search.model.search.Point
import runBlocking
import kotlin.test.Test


internal class DocAroundLatLng {

//    aroundLatLng: Point = Point(latitude, longitude)

    @Test
    fun query() {
        runBlocking {
            query("query") {
                aroundLatLng = Point(latitude = 40.71f, longitude = -74.01f)
            }
        }
    }
}