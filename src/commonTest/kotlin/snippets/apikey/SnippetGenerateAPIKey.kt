package snippets.apikey

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.Time
import com.algolia.search.model.apikey.SecuredAPIKeyRestriction
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.search.Query
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


class SnippetGenerateAPIKey : TestSnippets() {

//    fun ClientSearch.generateAPIKey(
//        [parentAPIKey](#method-param-apikey): __APIKey__,
//        restriction: __SecuredAPIKeyRestriction__
//    ): APIKey
//
//    public data class SecuredAPIKeyRestriction(
//        val [query](#method-param-searchparameter): __Query?__ = null,
//        val #{restrictIndices}: __List<IndexName>?__ = null,
//        val #{restrictSources}: __List<String>?__ = null,
//        val #{validUntil}: __Long?__ = null,
//        val #{userToken}: __UserToken?__ = null
//    )

    @Test
    fun generateAPIKeyQuery() {
        runBlocking {
            val parentAPIKey = APIKey("SearchOnlyApiKeyKeptPrivate")
            val restriction = SecuredAPIKeyRestriction(
                query = Query(filters = "_tags:user_42")
            )

            ClientSearch.generateAPIKey(parentAPIKey, restriction)
        }
    }

    @Test
    fun generateAPIKeyValidUntil() {
        val parentAPIKey = APIKey("SearchOnlyApiKeyKeptPrivate")
        val dayInMilliseconds = 60 * 60 * 24 * 1000
        val restriction = SecuredAPIKeyRestriction(
            validUntil = Time.getCurrentTimeMillis() + dayInMilliseconds
        )

        ClientSearch.generateAPIKey(parentAPIKey, restriction)
    }

    @Test
    fun generateAPIKeyRestrictIndices() {
        val parentAPIKey = APIKey("SearchOnlyApiKeyKeptPrivate")
        val restriction = SecuredAPIKeyRestriction(
            restrictIndices = listOf(
                IndexName("index1"),
                IndexName("index2")
            )
        )

        ClientSearch.generateAPIKey(parentAPIKey, restriction)
    }

    @Test
    fun generateAPIKeyRestrictSources() {
        val parentAPIKey = APIKey("SearchOnlyApiKeyKeptPrivate")
        val restriction = SecuredAPIKeyRestriction(
            restrictSources = listOf("192.168.1.0/24")
        )

        ClientSearch.generateAPIKey(parentAPIKey, restriction)
    }

    @Test
    fun generateAPIKeyUserToken() {
        val parentAPIKey = APIKey("SearchOnlyApiKeyKeptPrivate")
        val restriction = SecuredAPIKeyRestriction(
            query = Query(filters = "_tags:user_42"),
            userToken = UserToken("42")
        )

        ClientSearch.generateAPIKey(parentAPIKey, restriction)
    }
}