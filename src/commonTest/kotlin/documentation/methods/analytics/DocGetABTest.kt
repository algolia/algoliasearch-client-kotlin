package documentation.methods.analytics

import clientAnalytics
import com.algolia.search.model.analytics.ABTestID
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocGetABTest {

//    suspend fun ClientAnalytics.getABTest(
//        #{abTestID}: __ABTestID__,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseABTest

    @Test
    fun snippet1() {
        runBlocking {
            clientAnalytics.getABTest(ABTestID(42))
        }
    }
}
