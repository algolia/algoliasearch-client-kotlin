package documentation.methods.analytics

import clientAnalytics
import com.algolia.search.model.analytics.ABTestID
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocGetABTest {

//    suspend fun ClientAnalytics.getABTest(
//        #{abTestID}: __ABTestID__,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseABTest

    @Test
    fun snippet1() {
        runTest {
            clientAnalytics.getABTest(ABTestID(42))
        }
    }
}
