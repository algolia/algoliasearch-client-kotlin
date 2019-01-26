package com.algolia.search.saas.client

import com.algolia.search.saas.model.IndexName
import com.algolia.search.saas.model.settings.Settings
import com.algolia.search.saas.model.settings.SettingsKey
import com.algolia.search.saas.model.TaskUpdateIndex
import com.algolia.search.saas.endpoint.EndpointSettings
import com.algolia.search.saas.serialize.KeyForwardToReplicas
import com.algolia.search.saas.serialize.encodeNoNulls
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject


internal class ClientSettings(
    val client: Client,
    override val indexName: IndexName
) : EndpointSettings,
    Client by client {

    override suspend fun getSettings(requestOptions: RequestOptions?): Settings {
        return read.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/settings")) { path ->
            httpClient.get<Settings>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    private suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?,
        indexName: IndexName
    ): TaskUpdateIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/settings")) { path ->
            httpClient.put<TaskUpdateIndex>(path) {
                setRequestOptions(requestOptions)
                val map = settings
                    .encodeNoNulls()
                    .toMutableMap()
                    .apply {
                        resetToDefault.forEach {
                            put(it.raw, JsonNull)
                        }
                    }
                body = JsonObject(map).toString()
                forwardToReplicas?.let {
                    parameter(KeyForwardToReplicas, it)
                }
            }
        }
    }

    override suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): TaskUpdateIndex {
        return setSettings(settings, resetToDefault, forwardToReplicas, requestOptions, indexName)
    }

    override suspend fun copySettings(destination: IndexName, requestOptions: RequestOptions?): TaskUpdateIndex {
        val settings = getSettings(requestOptions)

        return setSettings(settings, requestOptions = requestOptions)
    }
}