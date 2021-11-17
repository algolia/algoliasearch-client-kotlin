package com.algolia.search.platform

import platform.Foundation.NSString

/**
 * Map Kotlin [String] as Objective-C [NSString].
 *
 * [documentation](https://kotlinlang.org/docs/native-objc-interop.html#mappings)
 */
internal fun String.nsString() = this as NSString
