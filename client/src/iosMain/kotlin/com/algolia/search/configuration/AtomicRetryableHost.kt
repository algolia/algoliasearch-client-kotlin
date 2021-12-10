package com.algolia.search.configuration

import com.algolia.search.configuration.internal.DefaultRetryableHost
import com.algolia.search.configuration.internal.EditableRetryableHost
import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic

internal class AtomicRetryableHost(
    private val ref: AtomicRef<DefaultRetryableHost>,
) : EditableRetryableHost() { // delegate to ref.value uses always the same (initial) reference.

    constructor(init: DefaultRetryableHost) : this(atomic(init))

    override fun edit(block: DefaultRetryableHost.() -> Unit) {
        ref.value = ref.value.clone().apply(block)
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

    override fun toString(): String = "AtomicRetryableHost(ref=$ref)"
}
