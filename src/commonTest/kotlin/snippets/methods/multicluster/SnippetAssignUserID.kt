package snippets.methods.multicluster

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import snippets.client
import kotlin.test.Test


internal class SnippetAssignUserID {

//    suspend fun ClientSearch.assignUserID(
//        #{userID}: __UserID__,
//        #{clusterName}: __ClusterName__,
//    ): Creation

    @Test
    fun assignUserID() {
        shouldFailWith<ResponseException> {
            runBlocking {
                client.assignUserID(UserID("myUserID1"), ClusterName("c1-test"))
            }
        }
    }
}