package com.algolia.search.helper


class StringUTF8 private constructor(val string: String) {

    companion object {

        fun encode(string: String) = StringUTF8(string.encodeUTF8())
    }
}

expect fun String.encodeUTF8(): String