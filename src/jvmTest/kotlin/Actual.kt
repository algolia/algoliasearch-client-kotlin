import com.algolia.search.client.ClientAnalytics
import com.algolia.search.client.ClientInsights
import com.algolia.search.client.ClientSearch
import com.algolia.search.helper.toAPIKey
import com.algolia.search.helper.toApplicationID
import com.algolia.search.helper.toIndexName
import kotlinx.coroutines.CoroutineScope
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext


internal actual val clientLatency
    get() = ClientSearch(
        System.getenv("ALGOLIA_KOTLIN_CLIENT_APP_ID").toApplicationID(),
        System.getenv("ALGOLIA_KOTLIN_CLIENT_ADMIN_KEY").toAPIKey()
    )
internal actual val clientSearch = ClientSearch(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_SEARCH_KEY_1").toAPIKey()
)
internal actual val clientAdmin1 = ClientSearch(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey()
)
internal actual val clientAdmin2 = ClientSearch(
    System.getenv("ALGOLIA_APPLICATION_ID_2").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_2").toAPIKey()
)
internal actual val clientMcm = ClientSearch(
    System.getenv("ALGOLIA_ADMIN_ID_MCM").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_MCM").toAPIKey()
)
internal actual val clientAnalytics = ClientAnalytics(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey()
)
internal actual val clientInsights = ClientInsights(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey()
)

internal actual val indexName = System.getenv("ALGOLIA_KOTLIN_CLIENT_INDEX").toIndexName()

internal actual val username: String
    get() {
        return try {
            System.getProperty("user.name")
        } catch (exception: Exception) {
            "unknown"
        }
    }

internal actual fun runBlocking(coroutineContext: CoroutineContext, block: suspend CoroutineScope.() -> Unit) {
    kotlinx.coroutines.runBlocking(coroutineContext, block = block)
}

internal actual object DateFormat {

    internal val dateFormat = SimpleDateFormat("YYYY-MM-dd-HH-mm-ss").also {
        it.timeZone = TimeZone.getTimeZone("UTC")
    }

    actual fun format(timestamp: Long?): String {
        return dateFormat.format(if (timestamp != null) Date(timestamp) else Date())
    }

    actual fun parse(date: String): Long {
        return dateFormat.parse(date).time
    }
}

internal actual fun loadScratch(name: String): String {
    return File("src/commonTest/resources/$name").readText()
}