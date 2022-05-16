package documentation.methods.multicluster

import com.algolia.search.model.multicluster.UserID
import documentation.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocRemoveUserID {

//    suspend fun ClientSearch.removeUserID(
//        #{userID}: __UserID__,
//        requestOptions: __RequestOptions?__ = null
//    ): Deletion

    @Test
    fun snippet1() {
        runTest {
            client.removeUserID(UserID("myUserID1"))
        }
    }
}
