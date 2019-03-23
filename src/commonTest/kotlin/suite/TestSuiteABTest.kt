package suite

import clientAdmin1
import clientAnalytics
import com.algolia.search.helper.DateISO8601
import com.algolia.search.model.Time
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyObjectID
import dayInMillis
import io.ktor.client.features.ResponseException
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.json
import runBlocking
import shouldEqual
import shouldFailWith
import shouldNotBeNull
import shouldNotEqual
import kotlin.test.BeforeTest
import kotlin.test.Test


internal class TestSuiteABTest {

    private val suffix = "ab_testing"
    private val indexNameA = testSuiteIndexName(suffix)
    private val indexNameB = indexNameA.copy(raw = indexNameA.raw + "_dev")
    private val indexA = clientAdmin1.initIndex(indexNameA)
    private val indexB = clientAdmin1.initIndex(indexNameB)
    private val data = json { KeyObjectID to "one" }

    private val abTest = ABTest(
        name = indexNameA.raw,
        variantA = Variant(indexNameA, 60, "a description"),
        variantB = Variant(indexNameB, 40),
        endAt = DateISO8601.format(Time.getCurrentTimeMillis() + dayInMillis)
    )

    @BeforeTest
    fun clean() {
        runBlocking {
            cleanABTest(suffix)
            cleanIndex(clientAdmin1, suffix)
        }
    }

    @Test
    fun test() {
        runBlocking {
            indexA.apply { saveObject(data).wait() shouldEqual TaskStatus.Published }
            indexB.apply { saveObject(data).wait() shouldEqual TaskStatus.Published }
            indexA.apply {
                val response = clientAnalytics.addABTest(abTest)

                response.wait() shouldEqual TaskStatus.Published
                clientAnalytics.getABTest(response.abTestID).let {
                    it.name shouldEqual abTest.name
                    it.endAt shouldEqual abTest.endAt
                    it.status shouldNotEqual ABTestStatus.Stopped
                    compareVariant(it.variantA, abTest.variantA)
                    compareVariant(it.variantB, abTest.variantB)
                }
                clientAnalytics.listABTests().abTests.find { it.abTestID == response.abTestID }.shouldNotBeNull()
                clientAnalytics.stopABTest(response.abTestID).wait() shouldEqual TaskStatus.Published
                clientAnalytics.getABTest(response.abTestID).status shouldEqual ABTestStatus.Stopped
                clientAnalytics.deleteABTest(response.abTestID).wait() shouldEqual TaskStatus.Published

                val result = ResponseException::class shouldFailWith {
                    clientAnalytics.getABTest(response.abTestID)
                }

                result.response.status.value shouldEqual HttpStatusCode.NotFound.value
            }
        }
    }
}