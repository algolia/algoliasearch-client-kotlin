package documentation.methods.analytics

import clientAnalytics
import com.algolia.search.model.analytics.ABTestID
import kotlinx.coroutines.test.runTest
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
        runTest {
            clientAnalytics.stopABTest(ABTestID(42))
        }
    }
}
