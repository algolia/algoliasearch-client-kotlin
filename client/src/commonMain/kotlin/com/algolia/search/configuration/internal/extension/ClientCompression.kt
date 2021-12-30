package com.algolia.search.configuration.internal.extension

import com.algolia.search.configuration.Compression
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.util.AttributeKey

/**
 * Adds content encoding header to http requests
 */
internal class ClientCompression(config: Configuration) {

    val compression = config.compression

    class Configuration(var compression: Compression = Compression.None)

    companion object Plugin : HttpClientPlugin<Configuration, ClientCompression> {

        override val key: AttributeKey<ClientCompression> = AttributeKey("ClientCompression")

        override fun prepare(block: Configuration.() -> Unit): ClientCompression =
            ClientCompression(Configuration().apply(block))

        override fun install(plugin: ClientCompression, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.Before) {
                val method = context.method
                if (method == HttpMethod.Post || method == HttpMethod.Put) {
                    when (plugin.compression) {
                        Compression.Gzip -> context.headers.append(HttpHeaders.ContentEncoding, "gzip")
                        Compression.None -> {
                            // empty.
                        }
                    }
                }
            }
        }
    }
}
