package snippets.multicluster

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserIDQuery
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import snippets.client
import kotlin.test.Test


internal class SnippetSearchUserID {

//    suspend fun ClientSearch.searchUserID(
//        query: __UserIDQuery__ = UserIDQuery(),
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseSearchUserID
//
//    data class UserIDQuery(
//        var #{query}: __String?__ = null,
//        var #{clusterName}: __ClusterName?__ = null,
//        var #{page}: __Int?__ = null,
//        var #{hitsPerPage}: __Int?__ = null
//    )

    @Test
    fun searchUserID() {
        shouldFailWith<ResponseException> {
            runBlocking {
                val query = UserIDQuery(
                    query = "query",
                    clusterName = ClusterName("c1-test"),
                    hitsPerPage = 12,
                    page = 0
                )
                client.searchUserID(query)
            }
        }
    }
}