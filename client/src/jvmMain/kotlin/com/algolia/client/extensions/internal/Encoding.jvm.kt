package com.algolia.client.extensions.internal

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

internal actual fun encodeKeySHA256(key: String, message: String): String {
  val rawHmac =
    Mac.getInstance("HmacSHA256").run {
      init(SecretKeySpec(key.toByteArray(), "HmacSHA256"))
      doFinal(message.toByteArray())
    }
  return Hex.buildString(rawHmac)
}
