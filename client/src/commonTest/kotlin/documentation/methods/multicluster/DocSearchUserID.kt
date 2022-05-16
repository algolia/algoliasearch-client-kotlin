package documentation.methods.multicluster

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserIDQuery
import documentation.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocSearchUserID {

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
    fun snippet1() {
        runTest {
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
