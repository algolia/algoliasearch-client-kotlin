package com.algolia.search.platform

import platform.Foundation.NSTimeInterval

/**
 * Converts [NSTimeInterval] to milliseconds, since it always specified as seconds.
 *
 * [documentation](https://developer.apple.com/documentation/foundation/nstimeinterval)
 */
internal fun NSTimeInterval.toMillis() = (this * 1000).toLong()
