package com.algolia.client.configuration.internal

import com.algolia.client.configuration.AgentSegment
import com.algolia.client.configuration.ClientOptions
import com.algolia.client.configuration.CompressionType
import io.ktor.client.*
import io.ktor.client.plugins.compression.*

internal actual fun platformAgentSegment(): AgentSegment =
  AgentSegment("JVM", System.getProperty("java.version"))

internal actual fun HttpClientConfig<*>.platformConfig(options: ClientOptions) {
  if (options.compressionType == CompressionType.GZIP) {
    install(GzipCompression)
  }
}

internal actual fun HttpClientConfig<*>.platformResponseDecompression() {
  // Engines such as CIO or Java neither advertise nor decode gzip on their own.
  install(ContentEncoding) {
    gzip()
    identity()
  }
}
