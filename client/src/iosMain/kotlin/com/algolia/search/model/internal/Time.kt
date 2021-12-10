package com.algolia.search.model.internal

import com.algolia.search.platform.toMillis
import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

internal actual object Time {

    actual fun getCurrentTimeMillis(): Long = NSDate().timeIntervalSince1970.toMillis()
}
