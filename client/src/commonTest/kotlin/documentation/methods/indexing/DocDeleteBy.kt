package documentation.methods.indexing

import com.algolia.search.dsl.requestOptions
import com.algolia.search.model.indexing.DeleteByQuery
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.model.search.Point
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocDeleteBy {

//    suspend fun Index.deleteObjectsBy(
//        [query](#method-param-filterParameters): __DeleteByQuery__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runTest {
            val query = DeleteByQuery(
                filters = "category:car",
                aroundLatLng = Point(40.71f, -74.01f)
            )

            index.deleteObjectsBy(query)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = DeleteByQuery(
                filters = "category:car",
                aroundLatLng = Point(40.71f, -74.01f)
            )
            val requestOptions = requestOptions {
                headerAlgoliaUserId(UserID("user123"))
            }

            index.deleteObjectsBy(query, requestOptions)
        }
    }
}
