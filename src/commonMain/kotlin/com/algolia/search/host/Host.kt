package com.algolia.search.host

import com.algolia.search.Time
import com.algolia.search.client.Configuration
import com.algolia.search.model.ApplicationID
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

internal fun List<HostStatuses>.selectNextHostIndex(): Int? {
    val hasUp = firstOrNull { it.first == HostStatus.Up }
    val hasUnknown = hasUp ?: firstOrNull { it.first == HostStatus.Unknown }

    return hasUnknown?.let(::indexOf)
}

internal fun List<String>.randomize(): List<String> {
    val destination = mutableListOf<String>()
    val source = toMutableList()

    for (index in source.indices) {
        destination += source.removeAt(Random.nextInt(0, source.size))
    }
    return destination
}

internal fun List<HostStatuses>.nextIndex(index: Int): Int {
    return if (index + 1 > lastIndex) 0 else index + 1
}

internal fun List<String>.initialHostStatus() = map { HostStatus.Unknown to 0L }.toMutableList()

internal fun ApplicationID.buildFallbackHosts(): List<String> {
    return listOf(
        "https://$this-1.algolianet.com",
        "https://$this-2.algolianet.com",
        "https://$this-3.algolianet.com"
    )
}

internal fun ApplicationID.readHosts(): List<String> {
    return listOf(readHost) + buildFallbackHosts().randomize()
}

internal fun ApplicationID.writeHosts(): List<String> {
    return listOf(writeHost) + buildFallbackHosts().randomize()
}

internal fun Configuration.readHosts(): List<String> {
    return hosts ?: applicationID.readHosts()
}

internal fun Configuration.writeHosts(): List<String> {
    return hosts ?: applicationID.writeHosts()
}