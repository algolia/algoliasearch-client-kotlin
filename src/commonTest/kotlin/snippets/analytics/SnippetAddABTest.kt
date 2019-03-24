package snippets.analytics

import clientAdmin1
import clientAnalytics
import com.algolia.search.model.ClientDate
import com.algolia.search.model.Time
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KeyObjectID
import kotlinx.serialization.json.json
import runBlocking
import suite.cleanABTest
import suite.cleanIndex
import suite.testSuiteIndexName
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test


internal class SnippetAddABTest {

//    suspend fun ClientAnalytics.addABTest(
//        #{abTest}: __ABTest__,
//        requestOptions: __RequestOptions?__ = null
//    ): CreationABTest

    private val suffix = "snippet"
    private val indexName = testSuiteIndexName(suffix)
    private val indexName1 = indexName
    private val indexName2 = indexName.copy(indexName.raw + "_copy")
    private val client = clientAdmin1
    private val index = clientAdmin1.initIndex(indexName)

    @Test
    fun addABTest() {
        runBlocking {
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
    fun addABTestCustomSearchParameters() {
        runBlocking {
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
                    customSearchParameters = Query(ignorePlurals = IgnorePlurals.Boolean(true))
                ),
                endAt = ClientDate(Time.getCurrentTimeMillis() + dayInMilliseconds)
            )

            clientAnalytics.addABTest(abTest)
        }
    }

    @BeforeTest
    fun before() {
        runBlocking {
            index.apply {
                saveObject(json { KeyObjectID to "myID" }).wait()
            }
            client.initIndex(indexName2).apply {
                saveObject(json { KeyObjectID to "myID" }).wait()
            }
        }
    }

    @AfterTest
    fun after() {
        runBlocking {
            cleanABTest(suffix, true)
            cleanIndex(client, suffix, true)
        }
    }
}