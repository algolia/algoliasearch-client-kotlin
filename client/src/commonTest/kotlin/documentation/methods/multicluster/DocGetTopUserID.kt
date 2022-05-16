package documentation.methods.multicluster

import documentation.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocGetTopUserID {

//    suspend fun ClientSearch.getTopUserID(
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseTopUserID

    @Test
    fun snippet1() {
        runTest {
            client.getTopUserID()
        }
    }
}
