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

    public val indexName: IndexName

    /**
     * Get the [Settings] of an index.
     *
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun getSettings(requestOptions: RequestOptions? = null): Settings

    /**
     * Create or change an index’s [Settings].
     * Only non-null settings are overridden; null settings are left unchanged
     * Performance wise, it’s better to [setSettings] before pushing the data.
     *
     * @param settings The [Settings] to be set.
     * @param resetToDefault Reset a settings to its default value.
     * @param forwardToReplicas Whether to forward the same settings to the replica indices.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey> = listOf(),
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex
}
