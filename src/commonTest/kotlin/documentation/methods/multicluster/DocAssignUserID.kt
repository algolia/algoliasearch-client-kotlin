package documentation.methods.multicluster

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import documentation.client
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocAssignUserID {

//    suspend fun ClientSearch.assignUserID(
//        #{userID}: __UserID__,
//        #{clusterName}: __ClusterName__,
//    ): Creation

    @Test
    fun assignUserID() {
        runBlocking {
            client.assignUserID(UserID("myUserID1"), ClusterName("c1-test"))
        }
    }
}