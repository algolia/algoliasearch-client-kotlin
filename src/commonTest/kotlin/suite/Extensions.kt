package suite

import DateFormat
import clientAnalytics
import com.algolia.search.client.ClientSearch
import com.algolia.search.helper.toIndexName
import com.algolia.search.model.IndexName
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.internal.Time
import com.algolia.search.model.response.ResponseVariant
import com.algolia.search.serialize.internal.JsonDebug
import dayInMillis
import kotlinx.serialization.KSerializer
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

internal suspend fun cleanIndex(client: ClientSearch, suffix: String, now: Boolean = false) {
    client.listIndices().items.forEach {
        val indexName = it.indexName.raw

        if (indexName.contains("kotlin")) {
            val result = Regex("kotlin-(.*)-$username-$suffix").find(indexName)
            val date = result?.groupValues?.get(1)
            if (date != null) {
                val difference = Time.getCurrentTimeMillis() - DateFormat.parse(date)

                if (difference >= dayInMillis || now) {
                    val index = client.initIndex(it.indexName)
                    if (it.abTestOrNull != null) {
                        index.apply {
                            clientAnalytics.deleteABTest(it.abTest.abTestId).wait()
                        }
                    }
                    index.apply { deleteIndex().wait() }
                }
            }
        }
    }
}

internal fun <T> load(serializer: KSerializer<T>, name: String): T {
    val string = loadScratch(name)
    val data = JsonDebug.decodeFromString(serializer, string)
    val serialized = JsonDebug.encodeToString(serializer, data)

    serialized.removeSpaces() shouldEqual string.removeSpaces()
    return data
}

private fun String.removeSpaces() = replace("\\s".toRegex(), "")
