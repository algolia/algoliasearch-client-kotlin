package com.algolia.search.helper.internal

import io.ktor.utils.io.core.toByteArray
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

internal actual fun String.sha256(key: String): String {
    val mac = Mac.getInstance("HmacSHA256")
    mac.init(SecretKeySpec(key.toByteArray(), "HmacSHA256"))
    val hash = mac.doFinal(toByteArray())
    return hash.toHex(true)
}
