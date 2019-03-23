package suite

import clientAdmin1
import clientAnalytics
import com.algolia.search.client.ClientSearch
import com.algolia.search.helper.toIndexName
import com.algolia.search.model.IndexName
import com.algolia.search.model.Time
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.response.ResponseVariant
import com.algolia.search.model.task.TaskStatus
import dayInMillis
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import loadScratch
import shouldEqual
import username

internal fun testSuiteIndexName(suffix: String): IndexName {
    val date = DateFormat.format()
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
                val difference = Time.getCurrentTimeMillis() - DateFormat.parse(date)

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
                val difference = Time.getCurrentTimeMillis() - DateFormat.parse(date)

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