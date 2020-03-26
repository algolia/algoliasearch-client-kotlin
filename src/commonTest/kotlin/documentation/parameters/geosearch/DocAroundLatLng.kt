package documentation.parameters.geosearch

import com.algolia.search.dsl.query
import com.algolia.search.model.search.Point
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocAroundLatLng {

//    aroundLatLng: Point = Point(latitude, longitude)

    @Test
    fun snippet1() {
        runBlocking {
            query("query") {
                aroundLatLng = Point(latitude = 40.71f, longitude = -74.01f)
            }
        }
    }
}