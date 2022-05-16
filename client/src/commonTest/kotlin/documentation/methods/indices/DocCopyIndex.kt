package documentation.methods.indices

import com.algolia.search.client.ClientAccount
import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.index.Scope
import documentation.index
import documentation.indexName
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocCopyIndex {

//    suspend fun [Index](#method-param-src)copyIndex(
//        [destination](#method-param-dst): __IndexName__,
//        [scopes](#method-param-scope): __List<Scope>?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex
//
//    suspend fun ClientAccount.copyIndex(
//        [source](#method-param-indexnamesrc): __Index__,
//        [destination](#method-param-indexnamedest): __Index__
//    ): List<Task>

    @Test
    fun snippet1() {
        runTest {
            index.copyIndex(indexName)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val scopes = listOf(
                Scope.Settings,
                Scope.Synonyms
            )
            index.copyIndex(indexName, scopes)
        }
    }

    @Test
    fun snippet3() {
        runTest {
            val index1 = ClientSearch(
                applicationID = ApplicationID("APP_ID_1"),
                apiKey = APIKey("API_KEY_1")
            ).initIndex(IndexName("index1"))

            val index2 = ClientSearch(
                applicationID = ApplicationID("APP_ID_2"),
                apiKey = APIKey("API_KEY_2")
            ).initIndex(IndexName("index2"))

            ClientAccount.copyIndex(index1, index2)
        }
    }
}
