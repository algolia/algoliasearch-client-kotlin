package com.algolia.search.helper


internal expect fun String.sha256(key: String): String

internal expect fun String.encodeBase64(): String