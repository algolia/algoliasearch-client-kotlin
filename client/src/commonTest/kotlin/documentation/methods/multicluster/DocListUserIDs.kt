package documentation.methods.multicluster

import documentation.client
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocListUserIDs {

//    suspend fun ClientSearch.listUserIDs(
//        #{page}: __Int?__ = null,
//        #{hitsPerPage}: __Int?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseListUserIDs

    @Test
    fun snippet1() {
        runTest {
            client.listUserIDs()
        }
    }
}
