package client.client

import client.data.IndexName
import client.data.Settings
import client.data.SettingsKey
import client.data.TaskSettings
import client.serialize.KeyForwardToReplicas
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTreeParser


class ClientSettings(
    val client: Client,
    override val indexName: IndexName
) : EndpointsSettings {

    override suspend fun getSettings(): Settings {
        return client.run {
            read.retry(readTimeout, indexName.pathIndexes("/settings")) { path ->
                Settings.deserialize(JsonTreeParser.parse(httpClient.get(path)))!!
            }
        }
    }

    override suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey>,
        forwardToReplicas: Boolean
    ): TaskSettings {
        return client.run {
            write.retry(writeTimeout, indexName.pathIndexes("/settings")) { path ->
                httpClient.put<TaskSettings>(path) {
                    val map = Settings.serialize(settings).toMutableMap().apply {
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