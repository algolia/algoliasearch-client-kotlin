package com.algolia.search.platform

import platform.Foundation.NSString

/**
 * Map Kotlin [String] as Objective-C [NSString].
 *
 * [documentation](https://kotlinlang.org/docs/native-objc-interop.html#mappings)
 */
@Suppress("CAST_NEVER_SUCCEEDS")
internal fun String.asNSString() = this as NSString
