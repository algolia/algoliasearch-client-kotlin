package suite

import com.algolia.search.browseAllABTests
import com.algolia.search.client.ClientAnalytics
import com.algolia.search.client.ClientSearch
import com.algolia.search.model.IndexName
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.toAPIKey
import com.algolia.search.toApplicationID
import com.algolia.search.toIndexName
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import shouldEqual
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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

internal val dateFormat = SimpleDateFormat("YYYY-MM-dd-HH-mm-ss").also {
    it.timeZone = TimeZone.getTimeZone("UTC")
}

internal fun testSuiteIndexName(suffix: String): IndexName {
    val date = dateFormat.format(Date())
    val prefix = "kotlin-$date"

    return "$prefix-qlitzler-$suffix".toIndexName()
}

internal suspend fun cleanABTest(suffix: String) {
    clientAnalytics.browseAllABTests {
        abTests.forEach {
            val result = Regex("kotlin-(.*)-qlitzler-$suffix").find(it.name)
            val date = result?.groupValues?.get(1)

            if (date != null) {
                val dayInMillis = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)
                val difference = Date().time - dateFormat.parse(date).time

                if (difference >= dayInMillis) {
                    clientAdmin1.initIndex(it.name.toIndexName()).apply {
                        clientAnalytics.deleteABTest(it.abTestID).wait() shouldEqual TaskStatus.Published
                    }
                }
            }
        }
    }
}

internal suspend fun cleanIndex(client: ClientSearch, suffix: String) {
    val indexToDelete = mutableListOf<IndexName>()
    client.listIndexes().items.forEach {
        val indexName = it.indexName.raw

        if (indexName.contains("kotlin")) {
            val result = Regex("kotlin-(.*)-qlitzler-$suffix").find(indexName)
            val date = result?.groupValues?.get(1)
            if (date != null) {
                val dayInMillis = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)
                val difference = Date().time - dateFormat.parse(date).time

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

internal fun loadScratch(name: String): File {
    return File("src/commonTest/scratches/$name")
}

internal fun <T> load(serializer: KSerializer<T>, name: String): T {
    val json = Json(indented = true, indent = "  ", encodeDefaults = false)
    val string = loadScratch(name).readText()
    val data = json.parse(serializer, string)
    val serialized = json.stringify(serializer, data)

    serialized shouldEqual string
    return data
}