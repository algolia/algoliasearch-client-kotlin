package com.algolia.search.transport

import io.ktor.util.GZip
import io.ktor.util.toByteArray
import kotlinx.coroutines.io.ByteReadChannel
import kotlinx.coroutines.runBlocking


internal actual object Gzip : (String) -> ByteArray {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override fun invoke(input: String): ByteArray {
        return runBlocking {
            GZip.run {
                encode(ByteReadChannel(input)).toByteArray()
            }
        }
    }
}