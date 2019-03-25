package snippets.apikey

import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.apikey.APIKeyParams
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.TypoTolerance
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import snippets.TestSnippets
import kotlin.test.Test


class SnippetAddAPIKey : TestSnippets() {

//    suspend fun ClientSearch.addAPIKey(
//        params: __APIKeyParams__,
//        #{restrictSources}: __String?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): CreationAPIKey
//
//    data class APIKeyParams(
//        val [ACLs](#method-param-acl): __List<ACL>?__ = null,
//        val [indices](#method-param-indexes): __List<IndexName>?__ = null,
//        val #{description}: __String?__ = null,
//        val #{maxHitsPerQuery}: __Int?__ = null,
//        val #{maxQueriesPerIPPerHour}: __Int?__ = null,
//        val #{validity}: __Long?__ = null,
//        val [query](#method-param-queryparameters): __Query?__ = null,
//        val #{referers}: __List<String>?__ = null
//    )

    @Test
    fun addAPIKey() {
        runBlocking {
            val params = APIKeyParams(
                ACLs = listOf(ACL.Search)
            )
            client.apply {
                val response = addAPIKey(params).wait()

                deleteAPIKey(response.apiKey)
            }
        }
    }

    @Test
    fun addAPIKeyAdvanced() {
        shouldFailWith<ResponseException> {
            runBlocking {

                val params = APIKeyParams(
                    ACLs = listOf(ACL.Search),
                    description = "Limited search only API key for algolia.com",
                    indices = listOf(IndexName("dev_*")),
                    maxHitsPerQuery = 20,
                    maxQueriesPerIPPerHour = 100,
                    query = Query(
                        typoTolerance = TypoTolerance.Strict,
                        ignorePlurals = IgnorePlurals.Boolean(false)
                    ),
                    referers = listOf("algolia.com/*"),
                    validity = 300
                )
                client.apply {
                    val response = client.addAPIKey(params, restrictSources = "169.254.0.0/16").wait()

                    client.deleteAPIKey(response.apiKey)
                }
            }
        }
    }
}