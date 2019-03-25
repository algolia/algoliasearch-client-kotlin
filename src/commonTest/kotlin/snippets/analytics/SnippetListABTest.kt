package snippets.analytics

import clientAnalytics
import runBlocking
import kotlin.test.Test


internal class SnippetListABTest {

//    suspend fun ClientAnalytics.listABTests(
//        [page](#method-param-offset): __Int?__ = null,
//        [hitsPerPage](#method-param-limit): __Int?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseABTests

    @Test
    fun listABTest() {
        runBlocking {
            clientAnalytics.listABTests(10, 20)
        }
    }
}