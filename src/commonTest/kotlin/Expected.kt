import com.algolia.search.client.ClientAnalytics
import com.algolia.search.client.ClientInsights
import com.algolia.search.client.ClientPlaces
import com.algolia.search.client.ClientRecommendation
import com.algolia.search.client.ClientSearch
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

internal expect val clientSearch: ClientSearch
internal expect val clientAdmin1: ClientSearch
internal expect val clientAdmin2: ClientSearch
internal expect val clientMcm: ClientSearch
internal expect val clientAnalytics: ClientAnalytics
internal expect val clientInsights: ClientInsights
internal expect val clientPlaces: ClientPlaces
internal expect val clientRecommendation: ClientRecommendation

internal expect fun runBlocking(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Unit
)

internal expect fun loadScratch(name: String): String

internal expect val username: String

internal const val dayInMillis = 24 * 60 * 60 * 1000

internal expect object DateFormat {

    fun format(timestamp: Long? = null): String

    fun parse(date: String): Long
}
