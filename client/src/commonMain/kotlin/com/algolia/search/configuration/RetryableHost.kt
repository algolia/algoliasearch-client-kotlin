package com.algolia.search.configuration

import com.algolia.search.model.internal.Time
import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic

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

internal data class DefaultRetryableHost(
    override val url: String,
    override val callType: CallType? = null
) : RetryableHost {

    override var isUp: Boolean = true
    override var lastUpdated: Long = Time.getCurrentTimeMillis()
    override var retryCount: Int = 0
}

internal class AtomicRetryableHost(
    private val reference: AtomicRef<DefaultRetryableHost>
) : RetryableHost by reference.value {

    constructor(init: DefaultRetryableHost) : this(atomic(init))

    fun edit(block: DefaultRetryableHost.() -> Unit) {
        val tmp = reference.value.copy()
        block(tmp)
        reference.value = tmp
    }

    override fun hashCode(): Int = reference.value.hashCode()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AtomicRetryableHost) return false
        return reference.value == other.reference.value
    }
}

internal fun RetryableHost.edit(block: DefaultRetryableHost.() -> Unit) {
    when (this) {
        is AtomicRetryableHost -> edit(block)
        is DefaultRetryableHost -> block(this)
    }
}
