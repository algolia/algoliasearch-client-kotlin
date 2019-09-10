package com.algolia.search.configuration

import com.algolia.search.model.Time
import kotlin.jvm.JvmOverloads


/**
 * @param url The url to target.
 * @param callType Whether this host should be used for [CallType.Read] or [CallType.Write] requests.
 */
public data class RetryableHost @JvmOverloads constructor(
    val url: String,
    val callType: CallType? = null
) {

    internal var isUp: Boolean = true
    internal var lastUpdated: Long = Time.getCurrentTimeMillis()
    internal var retryCount: Int = 0
}