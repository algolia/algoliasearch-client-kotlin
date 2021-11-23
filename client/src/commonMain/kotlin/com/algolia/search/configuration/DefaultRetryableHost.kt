package com.algolia.search.configuration

import com.algolia.search.model.internal.Time
import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic

internal data class DefaultRetryableHost(
    override val url: String,
    override val callType: CallType? = null,
) : RetryableHost {
    override var isUp: Boolean = true
    override var lastUpdated: Long = Time.getCurrentTimeMillis()
    override var retryCount: Int = 0
}

internal class AtomicRetryableHost(
    private val ref: AtomicRef<DefaultRetryableHost>,
) : RetryableHost { // delegate to ref.value uses always the same (initial) reference.

    constructor(init: DefaultRetryableHost) : this(atomic(init))

    fun deepEdit(block: DefaultRetryableHost.() -> Unit) {
        ref.value = ref.value.copy().apply(block)
    }

    override val url: String get() = ref.value.url
    override val callType: CallType? = ref.value.callType
    override val isUp: Boolean get() = ref.value.isUp
    override val lastUpdated: Long get() = ref.value.lastUpdated
    override val retryCount: Int get() = ref.value.retryCount

    override fun hashCode(): Int = ref.value.hashCode()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AtomicRetryableHost) return false
        return ref.value == other.ref.value
    }
}

internal fun RetryableHost.edit(block: DefaultRetryableHost.() -> Unit) {
    when (this) {
        is AtomicRetryableHost -> deepEdit(block)
        is DefaultRetryableHost -> block()
    }
}
