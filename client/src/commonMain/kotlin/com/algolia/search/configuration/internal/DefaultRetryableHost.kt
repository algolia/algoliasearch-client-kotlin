package com.algolia.search.configuration.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.RetryableHost
import com.algolia.search.model.internal.Time

internal abstract class EditableDefaultRetryableHost : RetryableHost {
    abstract fun edit(block: DefaultRetryableHost.() -> Unit)
}

internal data class DefaultRetryableHost(
    override val url: String,
    override val callType: CallType? = null
) : EditableDefaultRetryableHost() {

    override var isUp: Boolean = true
    override var lastUpdated: Long = Time.getCurrentTimeMillis()
    override var retryCount: Int = 0

    override fun edit(block: DefaultRetryableHost.() -> Unit) {
        block()
    }

    fun clone() = DefaultRetryableHost(url, callType).also {
        it.isUp = isUp
        it.lastUpdated = lastUpdated
        it.retryCount = retryCount
    }
}
