package com.algolia.search.saas.client

import com.algolia.search.saas.data.IndexName
import com.algolia.search.saas.data.Settings
import com.algolia.search.saas.data.SettingsKey
import com.algolia.search.saas.data.Task


interface EndpointsSettings {

    val indexName: IndexName

    suspend fun getSettings(requestOptions: RequestOptions? = null): Settings

    suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey> = listOf(),
        forwardToReplicas: Boolean = false,
        requestOptions: RequestOptions? = null
    ): Task
}