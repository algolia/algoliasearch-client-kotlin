package suite

import com.algolia.search.browseAllABTests
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
import shouldBeTrue
import shouldEqual
import shouldNotBeNull
import shouldNotEqual
import java.util.*
import java.util.concurrent.TimeUnit


@RunWith(JUnit4::class)
class TestSuiteABTest {

    private val suffix = "ab_testing"
    private val indexNameA = testSuiteIndexName(suffix)
    private val indexNameB = indexNameA.copy(raw = indexNameA.raw + "_dev")
    private val tomorrow = Date(Date().time + TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS))
    private val indexA = clientAdmin1.getIndex(indexNameA)
    private val indexB = clientAdmin1.getIndex(indexNameB)
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
            clientAnalytics.browseAllABTests(hitsPerPage = 1) {
                abTests?.forEach {
                    if (it.name.contains("kotlin")) {
                        clientAdmin1.getIndex(it.variantA.indexName).apply {
                            clientAnalytics.deleteABTest(it.abTestID).wait() shouldEqual TaskStatus.Published
                        }
                    }
                }
            }
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
                    it.variantA.indexName shouldEqual abTest.variantA.indexName
                    it.variantA.trafficPercentage shouldEqual abTest.variantA.trafficPercentage
                    it.variantA.description shouldEqual abTest.variantA.description
                    it.variantB.indexName shouldEqual abTest.variantB.indexName
                    it.variantB.trafficPercentage shouldEqual abTest.variantB.trafficPercentage
                    it.variantB.description shouldEqual abTest.variantB.description
                    it.status shouldNotEqual ABTestStatus.Stopped
                }
                clientAnalytics.listABTests().abTests!!.find { it.abTestID == response.abTestID }.shouldNotBeNull()
                clientAnalytics.stopABTest(response.abTestID).wait() shouldEqual TaskStatus.Published
                clientAnalytics.deleteABTest(response.abTestID).wait() shouldEqual TaskStatus.Published
                var hasThrown = false
                try {
                    clientAnalytics.getABTest(response.abTestID)
                } catch (exception: BadResponseStatusException) {
                    hasThrown = exception.statusCode.value == HttpStatusCode.NotFound.value
                }
                hasThrown.shouldBeTrue()
            }
        }
    }
}