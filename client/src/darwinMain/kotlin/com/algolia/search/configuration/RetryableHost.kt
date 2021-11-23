package com.algolia.search.configuration

import com.algolia.search.configuration.internal.DefaultRetryableHost

public actual fun RetryableHost(url: String, callType: CallType?): RetryableHost {
    return AtomicRetryableHost(init = DefaultRetryableHost(url, callType))
}
