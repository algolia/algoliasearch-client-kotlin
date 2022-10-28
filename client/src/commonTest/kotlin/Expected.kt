import com.algolia.search.client.ClientAnalytics
import com.algolia.search.client.ClientInsights
import com.algolia.search.client.ClientPersonalization
import com.algolia.search.client.ClientSearch

internal expect val clientSearch: ClientSearch
internal expect val clientAdmin1: ClientSearch
internal expect val clientAdmin2: ClientSearch
internal expect val clientMcm: ClientSearch
internal expect val clientAnalytics: ClientAnalytics
internal expect val clientInsights: ClientInsights
internal expect val clientPersonalization: ClientPersonalization
internal expect val clientAnswers: ClientSearch
internal expect fun loadScratch(name: String): String

internal expect val username: String

internal const val dayInMillis = 24 * 60 * 60 * 1000

internal expect object DateFormat {

    fun format(timestamp: Long? = null): String

    fun parse(date: String): Long
}

internal expect fun setupTrustStoreType()
