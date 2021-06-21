package com.algolia.search.helper.internal

private const val hexCode = "0123456789ABCDEF"

/**
 * Take the [ByteArray] and convert it to a hex encoded [String].
 *
 * @param lowerCase Print in lowercase, defaults to false.
 */
internal fun ByteArray.toHex(lowerCase: Boolean = false): String {
    val r = StringBuilder(size * 2)
    for (b in this) {
        r.append(hexCode[b.toInt() shr 4 and 0xF])
        r.append(hexCode[b.toInt() and 0xF])
    }
    return if (lowerCase) r.toString().lowercase() else r.toString()
}
