package com.algolia.search.configuration.internal.extension

import com.algolia.search.configuration.AlgoliaSearchClient
import com.algolia.search.configuration.clientUserAgent
import com.algolia.search.serialize.internal.Key
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.util.AttributeKey

/**
 * A plugin that adds Algolia agent to all requests.
 *
 * @property agent `X-Algolia-Agent` header value.
 */
internal class AlgoliaAgent private constructor(val agent: String) {

    class Config(var agent: String = clientUserAgent(AlgoliaSearchClient.version))

    companion object Plugin : HttpClientPlugin<Config, AlgoliaAgent> {

        override val key: AttributeKey<AlgoliaAgent> = AttributeKey("AlgoliaAgent")
        override fun prepare(block: Config.() -> Unit): AlgoliaAgent = AlgoliaAgent(Config().apply(block).agent)

        override fun install(plugin: AlgoliaAgent, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.State) {
                val parameters = context.url.parameters
                val key = Key.AlgoliaAgent
                if (parameters.contains(key)) {
                    val current = parameters[key]
                    parameters.remove(key)
                    parameters.append(key, "${plugin.agent}; $current")
                } else {
                    parameters.append(key, plugin.agent)
                }
            }
        }
    }
}
