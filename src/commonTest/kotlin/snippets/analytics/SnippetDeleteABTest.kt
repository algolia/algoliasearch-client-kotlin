package snippets.analytics

import clientAnalytics
import com.algolia.search.model.analytics.ABTestID
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import kotlin.test.Test


class SnippetDeleteABTest {

//    suspend fun ClientAnalytics.deleteABTest(
//        #{abTestID}: __ABTestID__,
//        requestOptions: __RequestOptions?__ = null
//    ): DeletionABTest

    @Test
    fun deleteABTest() {
        shouldFailWith<ResponseException> {
            runBlocking {
                clientAnalytics.stopABTest(ABTestID(42))
            }
        }
    }
}