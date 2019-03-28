package snippets.methods.indexing

import com.algolia.search.model.indexing.DeleteByQuery
import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.Point
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetDeleteBy : TestSnippets() {

//    suspend fun Index.deleteObjectsBy(
//        [query](#method-param-filterParameters): __DeleteByQuery__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun deleteObjectsBy() {
        runBlocking {
            val query = DeleteByQuery(
                filters = "category:car",
                aroundLatLng = Point(40.71f, -74.01f),
                aroundRadius = AroundRadius.InMeters(40)
            )

            index.deleteObjectsBy(query)
        }
    }
}