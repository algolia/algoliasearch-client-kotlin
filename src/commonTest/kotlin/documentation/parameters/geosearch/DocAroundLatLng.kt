package documentation.parameters.geosearch

import com.algolia.search.dsl.query
import com.algolia.search.model.search.Point
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

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
