package documentation.methods.apikey

import com.algolia.search.model.APIKey
import documentation.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocDeleteAPIKey {

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
    fun snippet1() {
        runTest {
            client.deleteAPIKey(APIKey("f420238212c54dcfad07ea0aa6d5c45f"))
        }
    }
}
