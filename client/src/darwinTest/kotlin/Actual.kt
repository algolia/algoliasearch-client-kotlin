import com.algolia.search.client.ClientAnalytics
import com.algolia.search.client.ClientInsights
import com.algolia.search.client.ClientPersonalization
import com.algolia.search.client.ClientPlaces
import com.algolia.search.client.ClientSearch
import com.algolia.search.configuration.Compression
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.Region
import com.algolia.search.helper.toAPIKey
import com.algolia.search.helper.toApplicationID
import com.algolia.search.platform.asString
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable
import kotlinx.cinterop.toKString
import kotlinx.coroutines.CoroutineScope
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSFileManager
import platform.Foundation.NSFullUserName
import platform.Foundation.NSTimeZone
import platform.Foundation.NSUUID
import platform.Foundation.dateWithTimeIntervalSince1970
import platform.Foundation.timeIntervalSince1970
import platform.Foundation.timeZoneForSecondsFromGMT
import platform.posix.getenv

@SharedImmutable
internal actual val clientSearch = ClientSearch(
    env("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    env("ALGOLIA_SEARCH_KEY_1").toAPIKey()
)
@SharedImmutable
internal actual val clientAdmin1 = ClientSearch(
    env("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    env("ALGOLIA_ADMIN_KEY_1").toAPIKey()
)

@SharedImmutable
internal actual val clientAdmin2 = ClientSearch(
    ConfigurationSearch(
        env("ALGOLIA_APPLICATION_ID_2").toApplicationID(),
        env("ALGOLIA_ADMIN_KEY_2").toAPIKey(),
        compression = Compression.None
    )
)

@SharedImmutable
internal actual val clientMcm = ClientSearch(
    env("ALGOLIA_ADMIN_ID_MCM").toApplicationID(),
    env("ALGOLIA_ADMIN_KEY_MCM").toAPIKey()
)
internal actual val clientAnalytics = ClientAnalytics(
    env("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    env("ALGOLIA_ADMIN_KEY_1").toAPIKey(),
    Region.Analytics.US
)
internal actual val clientInsights = ClientInsights(
    env("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    env("ALGOLIA_ADMIN_KEY_1").toAPIKey()
)

internal actual val clientPersonalization = ClientPersonalization(
    env("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    env("ALGOLIA_ADMIN_KEY_1").toAPIKey(),
    Region.Personalization.US
)

internal actual val clientPlaces = ClientPlaces(
    env("ALGOLIA_PLACES_APP_ID").toApplicationID(),
    env("ALGOLIA_PLACES_KEY").toAPIKey()
)

@SharedImmutable
internal actual val clientAnswers = ClientSearch(
    env("ALGOLIA_ANSWERS_APP_ID").toApplicationID(),
    env("ALGOLIA_ANSWERS_KEY").toAPIKey()
)

internal actual val username: String
    get() = NSFullUserName()

internal actual fun runBlocking(coroutineContext: CoroutineContext, block: suspend CoroutineScope.() -> Unit) {
    kotlinx.coroutines.runBlocking(coroutineContext, block = block)
}

internal actual object DateFormat {

    private val formatter = NSDateFormatter().also {
        it.dateFormat = "YYYY-MM-dd-HH-mm-ss"
        it.timeZone = NSTimeZone.timeZoneForSecondsFromGMT(0)
    }

    actual fun format(timestamp: Long?): String {
        val date = if (timestamp != null) NSDate.dateWithTimeIntervalSince1970(timestamp.toDouble()) else NSDate()
        return formatter.stringFromDate(date)
    }

    actual fun parse(date: String): Long {
        return formatter.dateFromString(date)?.timeIntervalSince1970?.toLong()
            ?: throw IllegalArgumentException("Can't parse date from $date")
    }
}

internal actual fun loadScratch(name: String): String {
    val manager = NSFileManager()
    return if (manager.fileExistsAtPath("src/commonTest/resources")) {
        manager.contentsAtPath("src/commonTest/resources/$name")?.asString()
    } else {
        manager.contentsAtPath("../../src/commonTest/resources/$name")?.asString()
    } ?: throw IllegalStateException("empty content for file $name")
}

internal actual fun setupTrustStoreType() {
    // NO-OP
}

internal actual fun randomUUID(): String = NSUUID().UUIDString

private fun env(name: String): String = requireNotNull(getenv(name)).toKString()
