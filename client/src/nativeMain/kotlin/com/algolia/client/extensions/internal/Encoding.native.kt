package com.algolia.client.extensions.internal

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.usePinned
import platform.CoreCrypto.CCHmac
import platform.CoreCrypto.CC_SHA256_DIGEST_LENGTH
import platform.CoreCrypto.kCCHmacAlgSHA256

internal actual fun encodeKeySHA256(key: String, message: String): String {
  val algorithm = kCCHmacAlgSHA256
  val keyBytes = key.encodeToByteArray()
  val messageBytes = message.encodeToByteArray()
  val digest = UByteArray(CC_SHA256_DIGEST_LENGTH)

  @OptIn(ExperimentalForeignApi::class)
  keyBytes.usePinned { keyPinned ->
    messageBytes.usePinned { messagePinned ->
      digest.usePinned { digestPinned ->
        CCHmac(
          algorithm,
          keyPinned.addressOf(0),
          keyBytes.size.convert(),
          messagePinned.addressOf(0),
          messageBytes.size.convert(),
          digestPinned.addressOf(0),
        )
      }
    }
  }

  return Hex.buildString(digest.toByteArray())
}
