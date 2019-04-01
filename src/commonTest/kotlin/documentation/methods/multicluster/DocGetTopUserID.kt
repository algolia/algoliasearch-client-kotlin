package documentation.methods.multicluster

import documentation.client
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocGetTopUserID {

//    suspend fun ClientSearch.getTopUserID(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseTopUserID

    @Test
    fun getTopUserID() {
        runBlocking {
            client.getTopUserID()
        }
    }
}