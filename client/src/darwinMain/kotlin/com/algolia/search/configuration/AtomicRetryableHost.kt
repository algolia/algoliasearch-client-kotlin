package com.algolia.search.configuration

import com.algolia.search.configuration.internal.DefaultRetryableHost
import com.algolia.search.configuration.internal.EditableDefaultRetryableHost
import kotlin.native.concurrent.AtomicReference

internal class AtomicRetryableHost(
    private val ref: AtomicReference<DefaultRetryableHost>,
) : EditableDefaultRetryableHost() { // delegate to ref.value uses always the same (initial) reference.

    constructor(init: DefaultRetryableHost) : this(AtomicReference(init))

    fun deepEdit(block: DefaultRetryableHost.() -> Unit) {
        ref.value = ref.value.copy().apply(block)
    }

    override fun edit(block: DefaultRetryableHost.() -> Unit) {
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
