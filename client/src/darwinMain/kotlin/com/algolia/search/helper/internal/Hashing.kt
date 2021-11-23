package com.algolia.search.helper.internal

import kotlinx.cinterop.UnsafeNumber
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.usePinned
import platform.CoreCrypto.CCHmac
import platform.CoreCrypto.CC_SHA256_DIGEST_LENGTH
import platform.CoreCrypto.kCCHmacAlgSHA256

@OptIn(ExperimentalUnsignedTypes::class, UnsafeNumber::class)
internal actual fun String.sha256(key: String): String {
    val input = encodeToByteArray()
    val keyData = key.encodeToByteArray()
    val digest = UByteArray(CC_SHA256_DIGEST_LENGTH)
    keyData.usePinned { keyPinned ->
        input.usePinned { inputPinned ->
            digest.usePinned { digestPinned ->
                CCHmac(
                    kCCHmacAlgSHA256,
                    keyPinned.addressOf(0),
                    keyData.size.convert(),
                    inputPinned.addressOf(0),
                    input.size.convert(),
                    digestPinned.addressOf(0)
                )
            }
        }
    }
    return digest.toByteArray().toHex(true)
}
