package com.algolia.search.util.internal

import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.ByteReadPacket
import io.ktor.utils.io.core.Input
import io.ktor.utils.io.core.String
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.readAvailable
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.core.writeFully
import io.ktor.utils.io.core.writeText
import kotlin.experimental.and

private const val BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
private const val BASE64_MASK: Byte = 0x3f
private const val BASE64_PAD = '='

private val BASE64_INVERSE_ALPHABET = IntArray(256) {
    BASE64_ALPHABET.indexOf(it.toChar())
}

/**
 * Encode [String] in base64 format and UTF-8 character encoding.
 */
internal fun String.encodeBase64(): String = buildPacket {
    writeText(this@encodeBase64)
}.encodeBase64()

/**
 * Encode [ByteArray] in base64 format
 */
internal fun ByteArray.encodeBase64(): String = buildPacket {
    writeFully(this@encodeBase64)
}.encodeBase64()

/**
 * Encode [ByteReadPacket] in base64 format
 */
internal fun ByteReadPacket.encodeBase64(): String = buildString {
    val data = ByteArray(3)
    while (remaining > 0) {
        val read = readAvailable(data)
        data.clearFrom(read)

        val padSize = (data.size - read) * 8 / 6
        val chunk = ((data[0].toInt() and 0xFF) shl 16) or
            ((data[1].toInt() and 0xFF) shl 8) or
            (data[2].toInt() and 0xFF)

        for (index in data.size downTo padSize) {
            val char = (chunk shr (6 * index)) and BASE64_MASK.toInt()
            append(char.toBase64())
        }

        repeat(padSize) { append(BASE64_PAD) }
    }
}

/**
 * Decode [String] from base64 format encoded in UTF-8.
 */
internal fun String.decodeBase64String(): String = String(decodeBase64Bytes(), charset = Charsets.UTF_8)

/**
 * Decode [String] from base64 format
 */
internal fun String.decodeBase64Bytes(): ByteArray = buildPacket {
    writeText(dropLastWhile { it == BASE64_PAD })
}.decodeBase64Bytes().readBytes()

/**
 * Decode [ByteReadPacket] from base64 format
 */

internal fun ByteReadPacket.decodeBase64Bytes(): Input = buildPacket {
    val data = ByteArray(4)

    while (remaining > 0) {
        val read = readAvailable(data)

        val chunk = data.foldIndexed(0) { index, result, current ->
            result or (current.fromBase64().toInt() shl ((3 - index) * 6))
        }

        for (index in data.size - 2 downTo (data.size - read)) {
            val origin = (chunk shr (8 * index)) and 0xff
            writeByte(origin.toByte())
        }
    }
}

internal fun ByteArray.clearFrom(from: Int) {
    (from until size).forEach { this[it] = 0 }
}

internal fun Int.toBase64(): Char = BASE64_ALPHABET[this]

internal fun Byte.fromBase64(): Byte = BASE64_INVERSE_ALPHABET[toInt() and 0xff].toByte() and BASE64_MASK
