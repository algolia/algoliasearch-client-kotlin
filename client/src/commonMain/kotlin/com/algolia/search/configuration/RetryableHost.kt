package com.algolia.search.configuration

public sealed interface RetryableHost {

    /** The url to target.*/
    public val url: String

    /** Whether this host should be used for [CallType.Read] or [CallType.Write] requests.*/
    public val callType: CallType?

    /** Is the host up */
    public val isUp: Boolean

    /** Last check timestamp */
    public val lastUpdated: Long

    /** Retry count */
    public val retryCount: Int
}

/**
 * Creates an instance of [RetryableHost].
 */
public fun RetryableHost(
    url: String,
    callType: CallType? = null
): RetryableHost {
    return AtomicRetryableHost(init = DefaultRetryableHost(url, callType))
}
