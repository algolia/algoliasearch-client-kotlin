package com.algolia.search.helper.internal

internal class StringUTF8 private constructor(val string: String) {

    public companion object {

        fun encode(string: String) = StringUTF8(string.encodeUTF8())
    }
}

internal expect fun String.encodeUTF8(): String
