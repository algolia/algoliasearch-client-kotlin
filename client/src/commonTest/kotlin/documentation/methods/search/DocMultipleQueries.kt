package documentation.methods.search

import com.algolia.search.dsl.requestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.search.Query
import documentation.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocMultipleQueries {

//    suspend fun ClientSearch.multipleQueries(
//        #{queries}: __List<IndexQuery>__,
//        #{strategy}: __MultipleQueriesStrategy?__ = null,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseSearches

    @Test
    fun snippet1() {
        runTest {
            val queries = listOf(
                IndexQuery(
                    IndexName("categories"),
                    Query(query = "query string", hitsPerPage = 3)
                ),
                IndexQuery(
                    IndexName("products"),
                    Query(query = "query string", hitsPerPage = 3, filters = "_tags:promotion")
                ),
                IndexQuery(
                    IndexName("products"),
                    Query(query = "query string", hitsPerPage = 10)
                )
            )

            client.multipleQueries(queries)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val queries = listOf(
                IndexQuery(
                    IndexName("categories"),
                    Query(query = "query string", hitsPerPage = 3)
                ),
                IndexQuery(
                    IndexName("products"),
                    Query(query = "query string", hitsPerPage = 3, filters = "_tags:promotion")
                ),
                IndexQuery(
                    IndexName("products"),
                    Query(query = "query string", hitsPerPage = 10)
                )
            )
            val requestOptions = requestOptions {
                header("X-Algolia-User-ID", "user123")
            }

            client.multipleQueries(queries, requestOptions = requestOptions)
        }
    }
}
