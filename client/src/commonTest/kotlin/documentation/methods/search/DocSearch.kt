package documentation.methods.search

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.attributesToRetrieve
import com.algolia.search.dsl.query
import com.algolia.search.dsl.requestOptions
import com.algolia.search.helper.deserialize
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.transport.customRequest
import documentation.client
import io.ktor.http.HttpMethod
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
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

    @Serializable
    data class Contact(
        val firstname: String,
        val lastname: String
    )

    @Test
    fun snippet1() {
        runTest {
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
        runTest {
            val indexName = IndexName("contacts")
            val index = client.initIndex(indexName)
            val query = Query("query")
            val requestOptions = requestOptions {
                header("X-Algolia-User-ID", "user123")
            }

            index.search(query, requestOptions)
        }
    }

    @Test
    fun snippet3() = runTest {
        client.customRequest<JsonElement>(
            method = HttpMethod.Post,
            callType = CallType.Read,
            path = "1/indexes/movies/query",
            body = buildJsonObject {
                put("query", JsonPrimitive("van"))
                put("hitsPerPage", JsonPrimitive(3))
            }.toString()
        )
    }
}
