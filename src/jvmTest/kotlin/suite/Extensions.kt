package suite

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.IndexName
import com.algolia.search.toAPIKey
import com.algolia.search.toApplicationID
import com.algolia.search.toIndexName
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

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

internal val dateFormat = SimpleDateFormat("YYYY-MM-dd-HH-mm-ss").also {
    it.timeZone = TimeZone.getTimeZone("UTC")
}

internal fun testSuiteIndexName(suffix: String): IndexName {
    val date = dateFormat.format(Date())
    val prefix = "kotlin-$date"

    return "$prefix-qlitzler-$suffix".toIndexName()
}

internal fun loadScratch(name: String): File {
    return File("/Users/quentinlitzler/Library/Preferences/IntelliJIdea2018.3/scratches/$name")
}

internal fun cleanIndex(client: ClientSearch, suffix: String) {
    runBlocking {
        val indexToDelete = mutableListOf<IndexName>()
        client.listIndexes().items.forEach {
            val indexName = it.indexName.raw

            if (indexName.contains("kotlin")) {
                val result = Regex("kotlin-(.*)-qlitzler-$suffix").find(indexName)
                val date = result?.groupValues?.get(1)

                if (date != null) {
//                    val dayInMillis = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)
//                    val difference = Date().time - dateFormat.parse(date).time
//                    if (difference >= dayInMillis) {
//                    }
                    indexToDelete += it.indexName
                    println("Deleted ${it.indexName}")
                }
            }
        }
        indexToDelete.forEach {
            client.getIndex(it).deleteIndex()
        }
    }
}

internal fun loadFileAsObjects(fileName: String): List<JsonObject> {
    val string = loadScratch(fileName).readText()

    return Json.plain.parseJson(string).jsonArray.map { it.jsonObject }
}