package suite

import clientAdmin1
import clientAnalytics
import com.algolia.search.model.ClientDate
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestStatus
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.internal.Time
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Query
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.internal.Key
import dayInMillis
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import setupTrustStoreType
import shouldEqual
import shouldNotEqual
import kotlin.test.Test

internal class TestSuiteAATest {

    private val suffix = "aa_testing"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)
    private val data = buildJsonObject { put(Key.ObjectID, "one") }
    private val abTest = ABTest(
        name = indexName.raw,
        endAt = ClientDate(Time.getCurrentTimeMillis() + dayInMillis),
        variantA = Variant(indexName, 90),
        variantB = Variant(
            indexName,
            10,
            customSearchParameters = Query(ignorePlurals = IgnorePlurals.True)
        )
    )

    init {
        setupTrustStoreType()
    }

    @Test
    fun test() {
        runTest {
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
