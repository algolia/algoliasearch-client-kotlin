package documentation.methods.multicluster

import documentation.client
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocListUserIDs {

//    suspend fun ClientSearch.listUserIDs(
//        #{page}: __Int?__ = null,
//        #{hitsPerPage}: __Int?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseListUserIDs

    @Test
    fun snippet1() {
        runBlocking {
            client.listUserIDs()
        }
    }
}
