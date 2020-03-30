package documentation.methods.multicluster

import documentation.client
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocGetTopUserID {

//    suspend fun ClientSearch.getTopUserID(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseTopUserID

    @Test
    fun snippet1() {
        runBlocking {
            client.getTopUserID()
        }
    }
}
