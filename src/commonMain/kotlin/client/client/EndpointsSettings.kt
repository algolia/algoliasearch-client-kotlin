package client.client

import client.data.IndexName
import client.data.Settings
import client.data.SettingsKey
import client.data.TaskSettings


interface EndpointsSettings {

    val indexName: IndexName

    suspend fun getSettings(): Settings

    suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey> = listOf(),
        forwardToReplicas: Boolean = false
    ): TaskSettings
}