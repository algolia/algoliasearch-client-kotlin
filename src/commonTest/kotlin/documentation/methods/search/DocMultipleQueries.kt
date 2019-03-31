package documentation.methods.search

import com.algolia.search.helper.requestOptionsBuilder
import com.algolia.search.model.IndexName
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.search.Query
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import documentation.client
import kotlin.test.Test


internal class DocMultipleQueries {

//    suspend fun ClientSearch.multipleQueries(
//        #{queries}: __List<IndexQuery>__,
//        #{strategy}: __MultipleQueriesStrategy?__ = null,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseSearches

    @Test
    fun example() {
        runBlocking {
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
    fun exampleExtraHeaders() {
        shouldFailWith<ResponseException> {
            runBlocking {
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
                val requestOptions = requestOptionsBuilder {
                    header("X-Algolia-User-ID", "user123")
                }

                client.multipleQueries(queries, requestOptions = requestOptions)
            }
        }
    }
}