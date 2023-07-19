package com.algolia.search.helper.internal

import com.algolia.search.platform.asNSString
import platform.Foundation.NSCharacterSet
import platform.Foundation.URLHostAllowedCharacterSet
import platform.Foundation.stringByAddingPercentEncodingWithAllowedCharacters

/**
 * Encodes [this] using UTF-8
 */
internal actual fun String.encodeUTF8(): String {
    return asNSString().stringByAddingPercentEncodingWithAllowedCharacters(allowedCharacters = NSCharacterSet.URLHostAllowedCharacterSet)!!
}
