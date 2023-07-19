package com.algolia.search.platform

import kotlinx.cinterop.UnsafeNumber
import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.dataUsingEncoding

/**
 * Converts [NSData] to [String] using UTF-8 encoding.
 */
@OptIn(UnsafeNumber::class)
internal fun NSData.asString(): String? {
    return NSString.create(this, NSUTF8StringEncoding) as String?
}

/**
 * Converts [String] to [NSData] using UTF-8 encoding.
 */
@OptIn(UnsafeNumber::class)
@Suppress("CAST_NEVER_SUCCEEDS")
public fun String.asNSData(): NSData? {
    return (this as NSString).dataUsingEncoding(NSUTF8StringEncoding)
}
