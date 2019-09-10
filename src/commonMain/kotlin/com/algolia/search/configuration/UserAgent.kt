@file:JvmName("UserAgentUtils")

package com.algolia.search.configuration

import kotlin.jvm.JvmName

@JvmName("client")
public fun clientUserAgent(version: String) = "Algolia for Kotlin ($version)"