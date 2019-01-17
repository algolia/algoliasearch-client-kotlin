package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.Settings
import com.algolia.search.saas.data.SettingsKey
import com.algolia.search.saas.data.TaskUpdateIndex


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