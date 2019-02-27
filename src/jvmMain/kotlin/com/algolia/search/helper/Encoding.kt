package com.algolia.search.helper

import java.net.URLEncoder


actual fun String.encodeUTF8(): String {
    return URLEncoder.encode(this, Charsets.UTF_8)
}