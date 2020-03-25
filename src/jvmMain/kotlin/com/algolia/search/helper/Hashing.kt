package com.algolia.search.helper

import io.ktor.utils.io.core.toByteArray
import java.util.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlinx.serialization.internal.HexConverter

internal actual fun String.sha256(key: String): String {
    return Mac.getInstance("HmacSHA256").run {
        val secretKey = SecretKeySpec(this@sha256.toByteArray(), "HmacSHA256")

        init(secretKey)
        val hash = doFinal(key.toByteArray())
        HexConverter.printHexBinary(hash, true)
    }
}

internal actual fun String.encodeBase64(): String {
    return String(Base64.getEncoder().encode(toByteArray()))
}

internal actual fun String.decodeBase64(): String {
    return String(Base64.getDecoder().decode(this))
}
