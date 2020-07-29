package com.algolia.search.endpoint

import com.algolia.search.model.APIKey
import com.algolia.search.model.apikey.APIKeyParams
import com.algolia.search.model.response.ResponseAPIKey
import com.algolia.search.model.response.ResponseListAPIKey
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.DeletionAPIKey
import com.algolia.search.model.response.revision.RevisionAPIKey
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/api-keys/?language=kotlin]
 */
public interface EndpointAPIKey {

    /**
     * Add a new [APIKey].
     *
     * @param params permissions/restrictions specified by [APIKeyParams]
     * @param restrictSources You can also add a restriction on the IPv4 network allowed to use the generated key.
     * This is used for more protection against [APIKey] leaking and reuse.
     * For security reasons, the creation of the key will fail if the server from which the key is created is not in the
     * restricted network. Example: "restrictSources=223.139.41".
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun addAPIKey(
        params: APIKeyParams,
        restrictSources: String? = null,
        requestOptions: RequestOptions? = null
    ): CreationAPIKey

    /**
     * Update the permissions of an existing [APIKey].
     *
     * @param apiKey [APIKey] to update
     * @param params permissions/restrictions specified by [APIKeyParams]
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun updateAPIKey(
        apiKey: APIKey,
        params: APIKeyParams,
        requestOptions: RequestOptions? = null
    ): RevisionAPIKey

    /**
     * Delete an existing [APIKey].
     * Be careful not to accidentally revoke a user’s access to the Dashboard by deleting any key that grants
     * such access. More generally: always be aware of a key’s privileges before deleting it, to avoid any unexpected
     * consequences.
     *
     * @param apiKey [APIKey] to delete
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun deleteAPIKey(
        apiKey: APIKey,
        requestOptions: RequestOptions? = null
    ): DeletionAPIKey

    /**
     * [APIKey] to restore.
     *
     * @param apiKey [APIKey] to restore
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun restoreAPIKey(
        apiKey: APIKey,
        requestOptions: RequestOptions? = null
    ): CreationAPIKey

    /**
     * Get the permissions of an [APIKey]. When initializing the client using the Admin [APIKey], you can request
     * information on any of your application’s API keys.
     * When using a non-admin [APIKey], you can only retrieve information on this specific [APIKey].
     *
     * @param apiKey [APIKey] to retrieve permissions for.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun getAPIKey(
        apiKey: APIKey,
        requestOptions: RequestOptions? = null
    ): ResponseAPIKey

    /**
     * Get the full list of API Keys.
     *
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun listAPIKeys(
        requestOptions: RequestOptions? = null
    ): ResponseListAPIKey
}
