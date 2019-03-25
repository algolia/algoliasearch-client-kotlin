package snippets.analytics

import clientAnalytics
import com.algolia.search.model.analytics.ABTestID
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import kotlin.test.Test


internal class SnippetStopABTest {

//    suspend fun ClientAnalytics.stopABTest(
//        #{abTestID}: __ABTestID__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionABTest

    @Test
    fun stopABTest() {
        shouldFailWith<ResponseException> {
            runBlocking {
                clientAnalytics.stopABTest(ABTestID(42))
            }
        }
    }
}