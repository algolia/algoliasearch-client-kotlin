package suite

import com.algolia.search.dateISO8601
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.search.BooleanOrQueryLanguages
import com.algolia.search.model.search.Query
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyObjectID
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import shouldNotEqual
import java.util.*
import java.util.concurrent.TimeUnit


@RunWith(JUnit4::class)
internal class TestSuiteAATest {

    private val suffix = "aa_testing"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)
    private val data = json { KeyObjectID to "one" }
    private val tomorrow = Date(Date().time + TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS))
    private val abTest = ABTest(
        name = indexName.raw,
        endAt = dateISO8601.format(tomorrow),
        variantA = Variant(indexName, 90),
        variantB = Variant(
            indexName,
            10,
            customSearchParameters = Query(ignorePlurals = BooleanOrQueryLanguages.Boolean(true))
        )
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
            index.apply {
                saveObject(data).wait() shouldEqual TaskStatus.Published
                val response = clientAnalytics.addABTest(abTest)

                response.wait() shouldEqual TaskStatus.Published
                clientAnalytics.getABTest(response.abTestID).let {
                    it.name shouldEqual abTest.name
                    it.endAt shouldEqual abTest.endAt
                    it.name shouldEqual abTest.name
                    it.endAt shouldEqual abTest.endAt
                    it.status shouldNotEqual ABTestStatus.Stopped
                    compareVariant(it.variantA, abTest.variantA)
                    compareVariant(it.variantB, abTest.variantB)
                }
            }
        }
    }
}