package suite

import DateFormat
import clientAnalytics
import com.algolia.search.client.ClientSearch
import com.algolia.search.helper.readContent
import com.algolia.search.helper.toIndexName
import com.algolia.search.model.IndexName
import com.algolia.search.model.Time
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.response.ResponseVariant
import com.algolia.search.serialize.JsonDebug
import dayInMillis
import io.ktor.client.features.ResponseException
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

internal suspend fun cleanABTest(clientSearch: ClientSearch, suffix: String, now: Boolean = false) {
    val regex = Regex("kotlin-(.*)-$username-$suffix")

    clientAnalytics.browseAllABTests(hitsPerPage = 100).forEach {
        it.abTests.forEach { abTest ->
            val result = regex.find(abTest.variantA.indexName.raw)
            val date = result?.groupValues?.get(1)

            if (date != null) {
                val difference = Time.getCurrentTimeMillis() - DateFormat.parse(date)

                if (difference >= dayInMillis || now) {
                    try {
                        val deletion = clientAnalytics.deleteABTest(abTest.abTestID)

                        clientSearch.initIndex(deletion.indexName).apply {
                            deletion.wait()
                        }
                    } catch (exception : ResponseException) {
                        println(exception.readContent())
                    }
                }
            }
        }
    }
}

internal suspend fun cleanIndex(client: ClientSearch, suffix: String, now: Boolean = false) {
    val indexToDelete = mutableListOf<IndexName>()

    client.listIndices().items.forEach {
        val indexName = it.indexName.raw

        if (indexName.contains("kotlin")) {
            val result = Regex("kotlin-(.*)-$username-$suffix").find(indexName)
            val date = result?.groupValues?.get(1)
            if (date != null) {
                val difference = Time.getCurrentTimeMillis() - DateFormat.parse(date)

                if (difference >= dayInMillis || now) {
                    indexToDelete += it.indexName
                }
            }
        }
    }
    indexToDelete.forEach {
        client.initIndex(it).apply {
            deleteIndex().wait()
        }
    }
}


internal fun <T> load(serializer: KSerializer<T>, name: String): T {
    val string = loadScratch(name)
    val data = JsonDebug.parse(serializer, string)
    val serialized = JsonDebug.stringify(serializer, data)

    serialized shouldEqual string
    return data
}