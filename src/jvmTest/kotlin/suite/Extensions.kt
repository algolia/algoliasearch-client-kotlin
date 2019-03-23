package suite

import com.algolia.search.client.ClientAnalytics
import com.algolia.search.client.ClientInsights
import com.algolia.search.client.ClientSearch
import com.algolia.search.helper.toAPIKey
import com.algolia.search.helper.toApplicationID
import com.algolia.search.helper.toIndexName
import com.algolia.search.model.IndexName
import com.algolia.search.model.Time
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.response.ResponseVariant
import com.algolia.search.model.task.TaskStatus
import dayInMillis
import getCurrentDateFormat
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import loadScratch
import parseDateFormat
import shouldEqual


internal val clientSearch = ClientSearch(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_SEARCH_KEY_1").toAPIKey()
)
internal val clientAdmin1 = ClientSearch(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey()
)
internal val clientAdmin2 = ClientSearch(
    System.getenv("ALGOLIA_APPLICATION_ID_2").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_2").toAPIKey()
)
internal val clientMcm = ClientSearch(
    System.getenv("ALGOLIA_ADMIN_ID_MCM").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_MCM").toAPIKey()
)
internal val clientAnalytics = ClientAnalytics(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey()
)
internal val clientInsights = ClientInsights(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey()
)

internal val username: String
    get() {
        return try {
            System.getProperty("user.name")
        } catch (exception: Exception) {
            "unknown"
        }
    }

internal fun testSuiteIndexName(suffix: String): IndexName {
    val date = getCurrentDateFormat()
    val prefix = "kotlin-$date"

    return "$prefix-$username-$suffix".toIndexName()
}

internal fun compareVariant(actual: ResponseVariant, expected: Variant) {
    actual.let {
        it.indexName shouldEqual expected.indexName
        it.trafficPercentage shouldEqual expected.trafficPercentage
        it.description shouldEqual expected.description
    }
}

internal suspend fun cleanABTest(suffix: String) {
    clientAnalytics.browseAllABTests {
        abTests.forEach {
            val result = Regex("kotlin-(.*)-$username-$suffix").find(it.name)
            val date = result?.groupValues?.get(1)

            if (date != null) {
                val difference = Time.getCurrentTimeMillis() - parseDateFormat(date)

                if (difference >= dayInMillis) {
                    clientAdmin1.initIndex(it.variantA.indexName).apply {
                        clientAnalytics.deleteABTest(it.abTestID).wait() shouldEqual TaskStatus.Published
                    }
                }
            }
        }
    }
}

internal suspend fun cleanIndex(client: ClientSearch, suffix: String) {
    val indexToDelete = mutableListOf<IndexName>()

    client.listIndices().items.forEach {
        val indexName = it.indexName.raw

        if (indexName.contains("kotlin")) {
            val result = Regex("kotlin-(.*)-$username-$suffix").find(indexName)
            val date = result?.groupValues?.get(1)
            if (date != null) {
                val difference = Time.getCurrentTimeMillis() - parseDateFormat(date)

                if (difference >= dayInMillis) {
                    indexToDelete += it.indexName
                }
            }
        }
    }
    indexToDelete.forEach {
        client.initIndex(it).deleteIndex()
    }
}


internal fun <T> load(serializer: KSerializer<T>, name: String): T {
    val json = Json(indented = true, indent = "  ", encodeDefaults = false)
    val string = loadScratch(name)
    val data = json.parse(serializer, string)
    val serialized = json.stringify(serializer, data)

    serialized shouldEqual string
    return data
}