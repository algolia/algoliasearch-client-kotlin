package com.algolia.search.helper

import android.util.Base64
import kotlinx.io.core.toByteArray
import kotlinx.serialization.internal.HexConverter
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


internal actual fun String.sha256(key: String): String {
    return Mac.getInstance("HmacSHA256").run {
        val secretKey = SecretKeySpec(this@sha256.toByteArray(), "HmacSHA256")

        init(secretKey)
        val hash = doFinal(key.toByteArray())
        HexConverter.printHexBinary(hash, true)
    }
}

internal actual fun String.encodeBase64(): String {
    return String(Base64.encode(toByteArray(), Base64.NO_WRAP))
}

internal actual fun String.decodeBase64(): String {
    return String(Base64.decode(this, Base64.NO_WRAP))
}