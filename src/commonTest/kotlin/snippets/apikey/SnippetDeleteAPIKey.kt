package snippets.apikey

import com.algolia.search.model.APIKey
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


class SnippetDeleteAPIKey : TestSnippets() {

//    suspend fun ClientSearch.deleteAPIKey(
//        #{apiKey}: __APIKey__,
//        requestOptions: __RequestOptions?__ = null
//    ): DeletionAPIKey
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
    fun deleteAPIKey() {
        runBlocking {
            client.deleteAPIKey(APIKey("f420238212c54dcfad07ea0aa6d5c45f"))
        }
    }
}