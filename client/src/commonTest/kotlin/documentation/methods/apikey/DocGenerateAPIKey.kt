package documentation.methods.apikey

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.SecuredAPIKeyRestriction
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.internal.Time
import com.algolia.search.model.search.Query
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocGenerateAPIKey {

//    fun ClientSearch.generateAPIKey(
//        [parentAPIKey](#method-param-apikey): __APIKey__,
//        restriction: __SecuredAPIKeyRestriction__
//    ): APIKey
//
//    data class SecuredAPIKeyRestriction(
//        val [query](#method-param-searchparameter): __Query?__ = null,
//        val #{restrictIndices}: __List<IndexName>?__ = null,
//        val #{restrictSources}: __List<String>?__ = null,
//        val #{validUntil}: __Long?__ = null,
//        val #{userToken}: __UserToken?__ = null
//    )

    @Test
    fun snippet1() {
        runTest {
            val parentAPIKey = APIKey("SearchOnlyApiKeyKeptPrivate")
            val restriction = SecuredAPIKeyRestriction(
                query = Query(filters = "_tags:user_42")
            )

            ClientSearch.generateAPIKey(parentAPIKey, restriction)
        }
    }

    @Test
    fun snippet2() {
        val parentAPIKey = APIKey("SearchOnlyApiKeyKeptPrivate")
        val dayInMilliseconds = 60 * 60 * 24 * 1000
        val restriction = SecuredAPIKeyRestriction(
            validUntil = Time.getCurrentTimeMillis() + dayInMilliseconds
        )

        ClientSearch.generateAPIKey(parentAPIKey, restriction)
    }

    @Test
    fun snippet3() {
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
    fun snippet4() {
        val parentAPIKey = APIKey("SearchOnlyApiKeyKeptPrivate")
        val restriction = SecuredAPIKeyRestriction(
            restrictSources = "192.168.1.0/24"
        )

        ClientSearch.generateAPIKey(parentAPIKey, restriction)
    }

    @Test
    fun snippet5() {
        val parentAPIKey = APIKey("SearchOnlyApiKeyKeptPrivate")
        val restriction = SecuredAPIKeyRestriction(
            query = Query(filters = "_tags:user_42"),
            userToken = UserToken("42")
        )

        ClientSearch.generateAPIKey(parentAPIKey, restriction)
    }
}
