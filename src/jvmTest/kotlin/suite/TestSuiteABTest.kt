package suite

import com.algolia.search.helper.dateISO8601
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyObjectID
import io.ktor.client.features.BadResponseStatusException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import shouldFailWith
import shouldNotBeNull
import shouldNotEqual
import java.util.*
import java.util.concurrent.TimeUnit


@RunWith(JUnit4::class)
internal class TestSuiteABTest {

    private val suffix = "ab_testing"
    private val indexNameA = testSuiteIndexName(suffix)
    private val indexNameB = indexNameA.copy(raw = indexNameA.raw + "_dev")
    private val tomorrow = Date(Date().time + TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS))
    private val indexA = clientAdmin1.initIndex(indexNameA)
    private val indexB = clientAdmin1.initIndex(indexNameB)
    private val data = json { KeyObjectID to "one" }

    private val abTest = ABTest(
        name = indexNameA.raw,
        variantA = Variant(indexNameA, 60, "a description"),
        variantB = Variant(indexNameB, 40),
        endAt = dateISO8601.format(tomorrow)
    )

    @Before
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

                val result = BadResponseStatusException::class shouldFailWith {
                    clientAnalytics.getABTest(response.abTestID)
                }

                result.statusCode.value shouldEqual HttpStatusCode.NotFound.value
            }
        }
    }
}