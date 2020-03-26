package com.algolia.search.helper

import java.net.URLEncoder

internal actual fun String.encodeUTF8(): String {
    return URLEncoder.encode(this, "UTF-8")
}
