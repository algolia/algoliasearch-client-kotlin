package com.algolia.search.client

import com.algolia.search.endpoint.EndpointSettings
import com.algolia.search.model.IndexName
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.settings.SettingsKey
import com.algolia.search.response.revision.RevisionIndex
import com.algolia.search.serialize.KeyForwardToReplicas
import com.algolia.search.serialize.merge
import com.algolia.search.serialize.toJsonNoDefaults
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.json


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
    ): RevisionIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/settings")) { path ->
            httpClient.put<RevisionIndex>(path) {
                setRequestOptions(requestOptions)
                val resets = json {
                    resetToDefault.forEach {
                        it.raw to JsonNull
                    }
                }
                body = settings
                    .toJsonNoDefaults()
                    .merge(resets).toString()
                parameter(KeyForwardToReplicas, forwardToReplicas)
            }
        }
    }

    override suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        return setSettings(settings, resetToDefault, forwardToReplicas, requestOptions, indexName)
    }

    override suspend fun copySettings(
        destination: IndexName,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        val settings = getSettings(requestOptions)

        return setSettings(settings, requestOptions = requestOptions)
    }
}