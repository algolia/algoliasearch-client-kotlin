package com.algolia.search.saas


actual fun String.encodeUTF8(): String {
    return this.toUtf8OrThrow().stringFromUtf8OrThrow()
}