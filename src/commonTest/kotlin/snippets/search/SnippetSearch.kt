package snippets.search

import com.algolia.search.filter.queryBuilder
import com.algolia.search.filter.setAttributesToRetrieve
import com.algolia.search.helper.requestOptionsBuilder
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import io.ktor.client.features.ResponseException
import kotlinx.serialization.Serializable
import runBlocking
import shouldFailWith
import snippets.client
import kotlin.test.Test


internal class SnippetSearch {

//     suspend fun Index.search(
//        #{query}: __Query?__ = null,
//        #{requestOptions}: __RequestOptions?__ = null
//     ): ResponseSearch
//
//     // any #{searchParameters} can be set on the Query object

    @Test
    fun example() {
        shouldFailWith<ResponseException> {
            runBlocking {
                @Serializable
                data class Contact(
                    val firstname: String,
                    val lastname: String
                )

                val indexName = IndexName("contacts")
                val index = client.initIndex(indexName)
                val query = queryBuilder {
                    query = "query string"
                    hitsPerPage = 50
                    setAttributesToRetrieve(Attribute("firstname"), Attribute("lastname"))
                }
                val response = index.search(query)

                response.hits.map { it.parse(Contact.serializer()) }
            }
        }
    }

    @Test
    fun exampleExtraHeaders() {
        shouldFailWith<ResponseException> {
            runBlocking {
                val indexName = IndexName("contacts")
                val index = client.initIndex(indexName)
                val query = Query("query")
                val requestOptions = requestOptionsBuilder {
                    header("X-Algolia-User-ID", "user123")
                }

                index.search(query, requestOptions)
            }
        }
    }
}