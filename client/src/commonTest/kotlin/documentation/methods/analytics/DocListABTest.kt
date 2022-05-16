package documentation.methods.analytics

import clientAnalytics
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocListABTest {

//    suspend fun ClientAnalytics.listABTests(
//        [page](#method-param-offset): __Int?__ = null,
//        [hitsPerPage](#method-param-limit): __Int?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseABTests

    @Test
    fun snippet1() {
        runTest {
            clientAnalytics.listABTests(10, 20)
        }
    }
}
