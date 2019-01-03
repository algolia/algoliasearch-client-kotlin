package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.Settings
import com.algolia.search.saas.data.SettingsKey
import com.algolia.search.saas.data.TaskUpdate
import com.algolia.search.saas.serialize.KeyForwardToReplicas
import com.algolia.search.saas.serialize.encodeNoNulls
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject


class ClientSettings(
    val client: Client,
    override val indexName: IndexName
) : EndpointsSettings {

    override suspend fun getSettings(requestOptions: RequestOptions?): Settings {
        return client.run {
            read.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/settings")) { path ->
                httpClient.get<Settings>(path) {
                    setRequestOptions(requestOptions)
                }
            }
        }
    }

    override suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey>,
        forwardToReplicas: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdate {
        return client.run {
            write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/settings")) { path ->
                httpClient.put<TaskUpdate>(path) {
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
                    parameter(KeyForwardToReplicas, forwardToReplicas)
                }
            }
        }
    }
}