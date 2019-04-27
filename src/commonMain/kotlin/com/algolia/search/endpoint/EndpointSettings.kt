package com.algolia.search.endpoint

import com.algolia.search.model.IndexName
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.settings.SettingsKey
import com.algolia.search.transport.RequestOptions


/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/settings/?language=kotlin]
 */
public interface EndpointSettings {

    val indexName: IndexName

    suspend fun getSettings(requestOptions: RequestOptions? = null): Settings

    suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey> = listOf(),
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex
}