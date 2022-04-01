package com.algolia.search.helper.internal

import io.ktor.http.encodeURLPath

internal actual fun String.encodeUTF8(): String {
    return encodeURLPath()
}
