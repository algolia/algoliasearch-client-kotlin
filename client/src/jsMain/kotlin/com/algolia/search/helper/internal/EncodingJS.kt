package com.algolia.search.helper.internal

import kotlinx.browser.window

internal actual fun String.encodeUTF8(): String {
    return window.btoa(this)
}
