package com.algolia.search.endpoint

import com.algolia.search.client.APIWrapper
import com.algolia.search.client.RequestOptions
import com.algolia.search.client.setRequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.settings.SettingsKey
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.serialize.KeyForwardToReplicas
import com.algolia.search.serialize.merge
import com.algolia.search.serialize.toJsonNoDefaults
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.json


internal class EndpointSettingsImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointSettings,
    APIWrapper by api {

    private val route = "/settings"

    override suspend fun getSettings(requestOptions: RequestOptions?): Settings {
        return read.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes(route)) { path ->
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
        val resets = json { resetToDefault.forEach { it.raw to JsonNull } }
        val bodyString = settings.toJsonNoDefaults().merge(resets).toString()

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes(route)) { path ->
            httpClient.put<RevisionIndex>(path) {
                body = bodyString
                parameter(KeyForwardToReplicas, forwardToReplicas)
                setRequestOptions(requestOptions)
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