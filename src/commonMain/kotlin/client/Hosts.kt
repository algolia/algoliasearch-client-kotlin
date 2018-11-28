package client

import kotlin.random.Random


internal class Hosts(appId: String) {

    companion object {

        private const val host = "algolianet.com"
    }

    val default = "$appId-dsn.algolia.net"

    private val hosts = mutableListOf(
        "$appId-1.$host",
        "$appId-2.$host",
        "$appId-3.$host"
    )

    val fallbackHosts = mutableListOf<String>()

    init {
        fallbackHosts += hosts.removeAt(Random.nextInt(0, hosts.size))
        fallbackHosts += hosts.removeAt(Random.nextInt(0, hosts.size))
        fallbackHosts += hosts.last()
    }
}