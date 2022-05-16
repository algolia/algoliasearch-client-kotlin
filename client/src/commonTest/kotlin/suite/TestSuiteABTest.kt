package suite

import clientAdmin1
import clientAnalytics
import com.algolia.search.exception.AlgoliaApiException
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.internal.Time
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.internal.Key
import dayInMillis
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import shouldEqual
import shouldFailWith
import shouldNotBeNull
import shouldNotEqual
import kotlin.test.Test

internal class TestSuiteABTest {

    private val suffix = "ab_testing"
    private val indexNameA = testSuiteIndexName(suffix)
    private val indexNameB = indexNameA.copy(raw = indexNameA.raw + "_dev")
    private val indexA = clientAdmin1.initIndex(indexNameA)
    private val indexB = clientAdmin1.initIndex(indexNameB)
    private val data = buildJsonObject { put(Key.ObjectID, "one") }

    private val abTest = ABTest(
        name = indexNameA.raw,
        variantA = Variant(indexNameA, 60, description = "a description"),
        variantB = Variant(indexNameB, 40),
        endAt = ClientDate(Time.getCurrentTimeMillis() + dayInMillis)
    )

    @Test
    fun test() {
        runTest {
            indexA.apply { saveObject(data).wait() shouldEqual TaskStatus.Published }
            indexB.apply { saveObject(data).wait() shouldEqual TaskStatus.Published }
            indexA.apply {
                val responseA = clientAnalytics.addABTest(abTest)

                responseA.wait() shouldEqual TaskStatus.Published
                clientAnalytics.getABTest(responseA.abTestID).let {
                    it.name shouldEqual abTest.name
                    it.endAt shouldEqual abTest.endAt
                    it.status shouldNotEqual ABTestStatus.Stopped
                    compareVariant(it.variantA, abTest.variantA)
                    compareVariant(it.variantB, abTest.variantB)
                }
                clientAnalytics.listABTests().abTests.find { it.abTestID == responseA.abTestID }.shouldNotBeNull()
                clientAnalytics.stopABTest(responseA.abTestID).wait() shouldEqual TaskStatus.Published
                clientAnalytics.getABTest(responseA.abTestID).status shouldEqual ABTestStatus.Stopped
                clientAnalytics.deleteABTest(responseA.abTestID).wait() shouldEqual TaskStatus.Published

                val responseB = shouldFailWith<AlgoliaApiException> {
                    clientAnalytics.getABTest(responseA.abTestID)
                }

                responseB.httpErrorCode shouldEqual HttpStatusCode.NotFound.value
            }
        }
    }
}
