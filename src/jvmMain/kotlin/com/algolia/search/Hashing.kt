package com.algolia.search

import kotlinx.io.core.toByteArray
import kotlinx.serialization.internal.HexConverter
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


actual fun String.sha256(key: String): String {
    return Mac.getInstance("HmacSHA256").run {
        val secretKey = SecretKeySpec(this@sha256.toByteArray(), "HmacSHA256")

        init(secretKey)
        val hash = doFinal(key.toByteArray())
        HexConverter.printHexBinary(hash, true)
    }
}

actual fun String.encodeBase64(): String {
    return String(Base64.getEncoder().encode(toByteArray()))
}