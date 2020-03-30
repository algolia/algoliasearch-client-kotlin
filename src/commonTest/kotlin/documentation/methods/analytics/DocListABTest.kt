package documentation.methods.analytics

import clientAnalytics
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocListABTest {

//    suspend fun ClientAnalytics.listABTests(
//        [page](#method-param-offset): __Int?__ = null,
//        [hitsPerPage](#method-param-limit): __Int?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseABTests

    @Test
    fun snippet1() {
        runBlocking {
            clientAnalytics.listABTests(10, 20)
        }
    }
}
