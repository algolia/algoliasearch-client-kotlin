package com.algolia.search.host

import com.algolia.search.client.ConfigurationImpl
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.Time
import kotlin.random.Random


internal data class HostStatus(
    val state: HostState,
    val timestamp: Long
)

internal val ApplicationID.readHost get() = "https://$this-dsn.algolia.net"
internal val ApplicationID.writeHost get() = "https://$this.algolia.net"

internal infix fun HostState.to(timestamp: Long) = HostStatus(this, timestamp)

internal fun HostState.getHostStatus() = this to Time.getCurrentTimeMillis()

internal fun List<HostStatus>.areStatusExpired(hostStatusExpirationDelay: Long): Boolean {
    val lastRequestTimestamp = maxBy { it.timestamp }?.timestamp ?: 0L
    val timeDelayExpired = Time.getCurrentTimeMillis() - hostStatusExpirationDelay

    return lastRequestTimestamp <= timeDelayExpired
}

internal fun List<HostStatus>.selectNextHostIndex(): Int? {
    val hasUp = firstOrNull { it.state == HostState.Up }
    val hasUnknown = hasUp ?: firstOrNull { it.state == HostState.Unknown }

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

internal fun List<HostStatus>.nextIndex(index: Int): Int {
    return if (index + 1 > lastIndex) 0 else index + 1
}

internal fun List<String>.initialHostStatus() = map { HostStatus(HostState.Unknown, 0L) }.toMutableList()

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

internal fun ConfigurationImpl.readHosts(): List<String> {
    return hosts ?: applicationID.readHosts()
}

internal fun ConfigurationImpl.writeHosts(): List<String> {
    return hosts ?: applicationID.writeHosts()
}