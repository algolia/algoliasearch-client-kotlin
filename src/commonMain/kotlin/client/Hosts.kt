package client

import kotlin.random.Random


internal class Hosts(appId: String) {

    companion object {

        private const val host = "algolianet.com"
    }

    val default = "$appId-dsn.algolia.net"

    val fallbackHosts = listOf(
        "$appId-1.$host",
        "$appId-2.$host",
        "$appId-3.$host"
    )

    internal fun getRandomFallbackHost() = fallbackHosts[Random.nextInt(0, fallbackHosts.size)]

    val fallback = getRandomFallbackHost()
}