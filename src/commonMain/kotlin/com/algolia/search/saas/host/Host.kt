package com.algolia.search.saas.host

import com.algolia.search.saas.Time
import com.algolia.search.saas.model.ApplicationID
import kotlin.random.Random


internal typealias HostStatuses = Pair<HostStatus, Long>

internal val ApplicationID.readHost get() = "https://$this-dsn.algolia.net"
internal val ApplicationID.writeHost get() = "https://$this.algolia.net"

internal fun HostStatus.getHostStatus() = this to Time.getCurrentTimeMillis()

internal fun List<HostStatuses>.areStatusExpired(hostStatusExpirationDelay: Long): Boolean {
    val lastRequestTimestamp = maxBy { it.second }?.second ?: 0L
    val someTimeAgo = Time.getCurrentTimeMillis() - hostStatusExpirationDelay

    return lastRequestTimestamp <= someTimeAgo
}

internal fun List<HostStatuses>.selectNextHostIndex(): Int {
    val hasUp = firstOrNull { it.first == HostStatus.Up }
    val hasUnknown = hasUp ?: firstOrNull { it.first == HostStatus.Unknown }

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

internal fun List<String>.initialHostStatus() = map { HostStatus.Unknown to 0L }.toMutableList()

internal fun ApplicationID.computeHosts(host: String = "algolianet.com"): List<String> {
    return listOf(
        "https://$this-1.$host",
        "https://$this-2.$host",
        "https://$this-3.$host"
    )
}
