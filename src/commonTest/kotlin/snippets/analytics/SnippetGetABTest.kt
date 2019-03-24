package snippets.analytics

import clientAnalytics
import com.algolia.search.model.analytics.ABTestID
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import kotlin.test.Test


class SnippetGetABTest {

//    suspend fun ClientAnalytics.getABTest(
//        #{abTestID}: __ABTestID__,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseABTest

    @Test
    fun getABTest() {
        shouldFailWith<ResponseException> {
            runBlocking {
                clientAnalytics.getABTest(ABTestID(42))
            }
        }
    }
}