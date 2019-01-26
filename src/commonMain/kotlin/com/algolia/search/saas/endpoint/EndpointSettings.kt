package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.model.IndexName
import com.algolia.search.saas.model.settings.Settings
import com.algolia.search.saas.model.settings.SettingsKey
import com.algolia.search.saas.model.common.TaskUpdate


interface EndpointSettings {

    val indexName: IndexName

    suspend fun getSettings(requestOptions: RequestOptions? = null): Settings

    suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey> = listOf(),
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdate

    suspend fun copySettings(destination: IndexName, requestOptions: RequestOptions? = null): TaskUpdate
}