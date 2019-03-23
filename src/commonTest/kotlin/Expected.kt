import com.algolia.search.client.ClientAnalytics
import com.algolia.search.client.ClientInsights
import com.algolia.search.client.ClientSearch
import kotlinx.coroutines.CoroutineScope

internal expect val clientSearch: ClientSearch
internal expect val clientAdmin1: ClientSearch
internal expect val clientAdmin2: ClientSearch
internal expect val clientMcm: ClientSearch
internal expect val clientAnalytics: ClientAnalytics
internal expect val clientInsights: ClientInsights

internal expect fun runBlocking(block: suspend CoroutineScope.() -> Unit)

internal expect fun getCurrentDateFormat(timestamp: Long? = null): String

internal expect fun parseDateFormat(date: String): Long

internal expect fun loadScratch(name: String): String

internal const val dayInMillis = 24 * 60 * 60 * 1000