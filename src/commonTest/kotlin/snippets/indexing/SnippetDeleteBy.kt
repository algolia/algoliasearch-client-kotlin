package snippets.indexing

import clientAdmin1
import com.algolia.search.model.indexing.DeleteByQuery
import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.ResponseFields
import io.ktor.client.features.ResponseException
import io.ktor.client.response.readBytes
import kotlinx.io.core.String
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.json
import runBlocking
import suite.cleanIndex
import suite.testSuiteIndexName
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test


internal class SnippetDeleteBy {

//    suspend fun deleteObjectBy(
//        query: __DeleteByQuery__,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): RevisionIndex

    private val suffix = "snippet"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @BeforeTest
    fun clean() {
        runBlocking { cleanIndex(clientAdmin1, suffix) }
    }

    @Test
    fun deleteObjectBy() {
        runBlocking {
            val query = DeleteByQuery(
                filters = "category:car",
                aroundLatLng = Point(40.71f, -74.01f),
                aroundRadius = AroundRadius.InMeters(40)
            )
            index.deleteObjectBy(query)
        }
    }
}