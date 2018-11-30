package client.host

import client.ApplicationId
import client.Time
import kotlin.random.Random


internal typealias HostStatus = Pair<Status, Long>

internal val ApplicationId.readHost get() = "$string-dsn.algolia.net"
internal val ApplicationId.writeHost get() = "$string.algolia.net"

internal fun Status.getHostStatus() = this to Time.getCurrentTimeMillis()

internal fun List<HostStatus>.areStatusExpired(hostStatusExpirationDelay: Long): Boolean {
    val lastRequestTimestamp = maxBy { it.second }?.second ?: 0L
    val someTimeAgo = Time.getCurrentTimeMillis() - hostStatusExpirationDelay

    return lastRequestTimestamp <= someTimeAgo
}

internal fun List<HostStatus>.selectNextHostIndex(): Int {
    val hasUp = firstOrNull { it.first == Status.Up }
    val hasUnknown = hasUp ?: firstOrNull { it.first == Status.Unknown }

    return indexOf(hasUnknown).coerceAtLeast(0)
}

internal fun List<String>.randomizeFallbackHosts(): List<String> {
    val fallbackHosts = mutableListOf<String>()
    val hosts = toMutableList()

    return fallbackHosts.apply {
        this += hosts.removeAt(Random.nextInt(0, hosts.size))
        this += hosts.removeAt(Random.nextInt(0, hosts.size))
        this += hosts.last()
    }
}

internal fun List<String>.initialHostStatus() = map { Status.Unknown to 0L }.toMutableList()

internal fun ApplicationId.computeFallbackHosts(host: String = "algolianet.com"): List<String> {
    return listOf(
        "https://$string-1.$host",
        "https://$string-2.$host",
        "https://$string-3.$host"
    )
}
