package com.algolia.search.configuration.internal.extension

import com.algolia.search.configuration.RetryableHost
import com.algolia.search.configuration.internal.DefaultRetryableHost
import com.algolia.search.configuration.internal.EditableDefaultRetryableHost

internal fun RetryableHost.edit(block: DefaultRetryableHost.() -> Unit) {
    when (this) {
        is EditableDefaultRetryableHost -> edit(block)
    }
}
