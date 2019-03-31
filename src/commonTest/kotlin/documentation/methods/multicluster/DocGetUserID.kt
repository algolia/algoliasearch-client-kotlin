package documentation.methods.multicluster

import com.algolia.search.model.multicluster.UserID
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import documentation.client
import kotlin.test.Test


internal class DocGetUserID {

//    suspend fun ClientSearch.getUserID(
//        #{userID}: __UserID__,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseUserID

    @Test
    fun getUserID() {
        shouldFailWith<ResponseException> {
            runBlocking {
                client.getUserID(UserID("myUserID1"))
            }
        }
    }
}