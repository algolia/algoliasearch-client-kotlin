package client.host

import client.Time
import client.data.ApplicationId
import kotlin.random.Random


internal typealias HostStatus = Pair<Status, Long>

internal val ApplicationId.readHost get() = "https://$name-dsn.algolia.net"
internal val ApplicationId.writeHost get() = "https://$name.algolia.net"

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

internal fun List<String>.randomize(): List<String> {
    val destination = mutableListOf<String>()
    val source = toMutableList()

    for (index in source.indices) {
        destination += source.removeAt(Random.nextInt(0, source.size))
    }
    return destination
}

internal fun List<String>.initialHostStatus() = map { Status.Unknown to 0L }.toMutableList()

internal fun ApplicationId.computeHosts(host: String = "algolianet.com"): List<String> {
    return listOf(
        "https://$name-1.$host",
        "https://$name-2.$host",
        "https://$name-3.$host"
    )
}
