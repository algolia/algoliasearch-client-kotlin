package documentation.methods.multicluster

import com.algolia.search.model.multicluster.UserID
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import documentation.client
import kotlin.test.Test


internal class DocRemoveUserID {

//    suspend fun ClientSearch.removeUserID(
//        #{userID}: __UserID__,
//        requestOptions: __RequestOptions?__ = null
//    ): Deletion

    @Test
    fun removeUserID() {
        shouldFailWith<ResponseException> {
            runBlocking {
                client.removeUserID(UserID("myUserID1"))
            }
        }
    }
}