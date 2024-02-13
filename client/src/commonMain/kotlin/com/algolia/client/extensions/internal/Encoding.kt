package com.algolia.client.extensions.internal

/**
 * Encodes a key using sha256.
 */
internal expect fun encodeKeySHA256(key: String, message: String): String

/**
 * Hex utilities.
 */
internal object Hex {

  private const val HEX_CODE = "0123456789abcdef"

  /**
   * Take the [ByteArray] and convert it to a hex encoded [String].
   */
  internal fun buildString(byteArray: ByteArray): String {
    return buildString(capacity = byteArray.size * 2) {
      for (byte in byteArray) {
        append(HEX_CODE[byte.toInt() shr 4 and 0xF])
        append(HEX_CODE[byte.toInt() and 0xF])
      }
    }
  }
}
