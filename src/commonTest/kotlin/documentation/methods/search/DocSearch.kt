package documentation.methods.search

import com.algolia.search.dsl.attributesToRetrieve
import com.algolia.search.dsl.query
import com.algolia.search.dsl.requestOptions
import com.algolia.search.helper.deserialize
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import documentation.client
import kotlinx.serialization.Serializable
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocSearch {

//     suspend fun Index.search(
//        #{query}: __Query__ = Query(),
//        #{requestOptions}: __RequestOptions?__ = null
//     ): ResponseSearch
//
//     // any #{searchParameters} can be set on the Query object

    @Test
    fun snippet1() {
        runBlocking {
            @Serializable
            data class Contact(
                val firstname: String,
                val lastname: String
            )

            val indexName = IndexName("contacts")
            val index = client.initIndex(indexName)
            val query = query("query") {
                hitsPerPage = 50
                attributesToRetrieve {
                    +"firstname"
                    +"lastname"
                }
            }
            val response = index.search(query)

            response.hits.deserialize(Contact.serializer())
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val indexName = IndexName("contacts")
            val index = client.initIndex(indexName)
            val query = Query("query")
            val requestOptions = requestOptions {
                header("X-Algolia-User-ID", "user123")
            }

            index.search(query, requestOptions)
        }
    }
}