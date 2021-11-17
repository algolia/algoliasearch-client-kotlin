package com.algolia.search.platform

import platform.Foundation.NSTimeInterval

internal fun NSTimeInterval.toMillis() = (this * 1000).toLong()
