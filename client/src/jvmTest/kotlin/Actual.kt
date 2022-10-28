import com.algolia.search.client.ClientAnalytics
import com.algolia.search.client.ClientInsights
import com.algolia.search.client.ClientPersonalization
import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.Compression
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.Region
import com.algolia.search.helper.toAPIKey
import com.algolia.search.helper.toApplicationID
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

internal actual val clientSearch = ClientSearch(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_SEARCH_KEY_1").toAPIKey()
)
internal actual val clientAdmin1 = ClientSearch(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey()
)
internal actual val clientAdmin2 = ClientSearch(
    ConfigurationSearch(
        System.getenv("ALGOLIA_APPLICATION_ID_2").toApplicationID(),
        System.getenv("ALGOLIA_ADMIN_KEY_2").toAPIKey(),
        compression = Compression.None
    )
)
internal actual val clientMcm = ClientSearch(
    System.getenv("ALGOLIA_ADMIN_ID_MCM").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_MCM").toAPIKey()
)
internal actual val clientAnalytics = ClientAnalytics(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey(),
    Region.Analytics.US
)
internal actual val clientInsights = ClientInsights(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey()
)

internal actual val clientPersonalization = ClientPersonalization(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey(),
    Region.Personalization.US
)

internal actual val clientAnswers = ClientSearch(
    System.getenv("ALGOLIA_ANSWERS_APP_ID").toApplicationID(),
    System.getenv("ALGOLIA_ANSWERS_KEY").toAPIKey()
)

internal actual val username: String
    get() {
        return try {
            System.getProperty("user.name")
        } catch (exception: Exception) {
            "unknown"
        }
    }

internal actual object DateFormat {

    private val dateFormat = SimpleDateFormat("YYYY-MM-dd-HH-mm-ss").also {
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
    return if (File("src/commonTest/resources").exists()) {
        File("src/commonTest/resources/$name").readText()
    } else {
        File("../../src/commonTest/resources/$name").readText()
    }
}

internal actual fun setupTrustStoreType() {
    System.setProperty("javax.net.ssl.trustStoreType", "JKS")
}
