package com.algolia.search.configuration.internal.extension

import com.algolia.search.exception.AlgoliaClientException
import com.algolia.search.serialize.internal.Key
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.util.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject

internal class ApiKeyPlugin private constructor(val config: Config) {
    class Config(var maxLength: Int = 500)

    companion object Plugin : HttpClientPlugin<Config, ApiKeyPlugin> {
        override val key = AttributeKey<ApiKeyPlugin>("ApiKeyPlugin")

        override fun prepare(block: Config.() -> Unit): ApiKeyPlugin {
            val config = Config().apply(block)
            return ApiKeyPlugin(config)
        }

        override fun install(plugin: ApiKeyPlugin, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.Transform) { payload ->
                if (context.method == HttpMethod.Get || context.method == HttpMethod.Delete) return@intercept
                val apiKey = context.headers[Key.AlgoliaAPIKey]
                if (apiKey == null || apiKey.length < plugin.config.maxLength) {
                    return@intercept
                }
                require(payload is String) { "Unsupported body type: $payload" }
                context.headers.remove("X-Algolia-API-Key")
                val textContent = patchRequestBody(payload, apiKey)
                proceedWith(textContent)
            }
        }

        /**
         * Patches the request body with the API key.
         * If the body is empty, it creates a new JSON object with the API key.
         * If the body is not empty, it adds the API key to the existing JSON object (assumes it's a valid JSON object).
         */
        internal fun patchRequestBody(payload: String, apiKey: String): TextContent {
            val patchedJsonObject = if (payload.isEmpty()) {
                buildJsonObject {
                    put("apiKey", JsonPrimitive(apiKey))
                }
            } else {
                val jsonObject = Json.parseToJsonElement(payload) as? JsonObject
                    ?: throw AlgoliaClientException("Failed to patch JSON body")
                val jsonMap = jsonObject.toMutableMap()
                jsonMap["apiKey"] = JsonPrimitive(apiKey)
                JsonObject(jsonMap)
            }
            val json = Json.encodeToString(JsonObject.serializer(), patchedJsonObject)
            val textContent = TextContent(
                text = json,
                contentType = ContentType.Application.Json,
            )
            return textContent
        }
    }
}