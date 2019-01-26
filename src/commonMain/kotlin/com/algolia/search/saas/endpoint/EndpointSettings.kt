package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.model.IndexName
import com.algolia.search.saas.model.Settings
import com.algolia.search.saas.model.SettingsKey
import com.algolia.search.saas.model.TaskUpdateIndex


interface EndpointSettings {

    val indexName: IndexName

    suspend fun getSettings(requestOptions: RequestOptions? = null): Settings

    suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey> = listOf(),
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdateIndex

    suspend fun copySettings(destination: IndexName, requestOptions: RequestOptions? = null): TaskUpdateIndex
}