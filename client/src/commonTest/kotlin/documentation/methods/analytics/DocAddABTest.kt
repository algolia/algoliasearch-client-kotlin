package documentation.methods.analytics

import clientAnalytics
import com.algolia.search.model.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.internal.Time
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Query
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocAddABTest {

//    suspend fun ClientAnalytics.addABTest(
//        #{abTest}: __ABTest__,
//        requestOptions: __RequestOptions?__ = null
//    ): CreationABTest

    private val indexName1 = IndexName("index1")
    private val indexName2 = IndexName("index2")

    @Test
    fun snippet1() {
        runTest {
            val dayInMilliseconds = 60 * 60 * 24 * 1000
            val abTest = ABTest(
                name = "myABTest",
                variantA = Variant(
                    indexName = indexName1,
                    trafficPercentage = 90,
                    description = "a description"
                ),
                variantB = Variant(
                    indexName = indexName2,
                    trafficPercentage = 10,
                    description = "a description"
                ),
                endAt = ClientDate(Time.getCurrentTimeMillis() + dayInMilliseconds)
            )

            clientAnalytics.addABTest(abTest)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val dayInMilliseconds = 60 * 60 * 24 * 1000
            val abTest = ABTest(
                name = "myABTest",
                variantA = Variant(
                    indexName = indexName1,
                    trafficPercentage = 90,
                    description = "a description"
                ),
                variantB = Variant(
                    indexName = indexName1,
                    trafficPercentage = 10,
                    description = "a description",
                    customSearchParameters = Query(ignorePlurals = IgnorePlurals.True)
                ),
                endAt = ClientDate(Time.getCurrentTimeMillis() + dayInMilliseconds)
            )

            clientAnalytics.addABTest(abTest)
        }
    }
}
