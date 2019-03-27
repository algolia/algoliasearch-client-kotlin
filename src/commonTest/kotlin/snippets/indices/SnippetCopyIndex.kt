package snippets.indices

import com.algolia.search.client.ClientAccount
import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.index.Scope
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetCopyIndex : TestSnippets() {

//    suspend fun [Index](#method-param-src)copyIndex(
//        [destination](#method-param-dst): __IndexName__,
//        [scopes](#method-param-scope): __List<Scope>?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

//    suspend fun ClientAccount.copyIndex(
//        [source](#method-param-indexnamesrc): __Index__,
//        [destination](#method-param-indexnamedest): __Index__
//    ): List<Task>

    @Test
    fun copyIndex() {
        shouldFailWith<ResponseException> {
            runBlocking {
                index.copyIndex(indexName)
            }
        }
    }

    @Test
    fun copyIndexScope() {
        shouldFailWith<ResponseException> {
            runBlocking {
                val scopes = listOf(
                    Scope.Settings,
                    Scope.Synonyms
                )
                index.copyIndex(indexName, scopes)
            }
        }
    }

    @Test
    fun copyAccount() {
        shouldFailWith<Exception> {
            runBlocking {
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
}