package documentation.methods.multicluster

import com.algolia.search.model.multicluster.UserID
import documentation.client
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocGetUserID {

//    suspend fun ClientSearch.getUserID(
//        #{userID}: __UserID__,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseUserID

    @Test
    fun snippet1() {
        runBlocking {
            client.getUserID(UserID("myUserID1"))
        }
    }
}
