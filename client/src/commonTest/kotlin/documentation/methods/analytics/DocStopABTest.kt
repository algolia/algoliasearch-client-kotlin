package documentation.methods.analytics

import clientAnalytics
import com.algolia.search.model.analytics.ABTestID
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocStopABTest {

//    suspend fun ClientAnalytics.stopABTest(
//        #{abTestID}: __ABTestID__,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionABTest

    @Test
    fun snippet1() {
        runBlocking {
            clientAnalytics.stopABTest(ABTestID(42))
        }
    }
}
