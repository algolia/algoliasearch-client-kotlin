package com.algolia.search.saas.client

import com.algolia.search.saas.data.*


interface EndpointsAPIKey {

    suspend fun addAPIKey(
        rights: List<ACL>? = null,
        indexes: List<Index>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        queryParameters: Query? = null,
        referers: List<String>? = null
    ): TaskCreate

    suspend fun listAPIKeys(): APIKeys

    suspend fun updateAPIKey(
        rights: List<ACL>? = null,
        indexes: List<Index>? = null,
        description: String? = null,
        maxHitsPerQuery: Int? = null,
        maxQueriesPerIPPerHour: Int? = null,
        validity: Long? = null,
        queryParameters: Query? = null,
        referers: List<String>? = null
    ): TaskCreate

    suspend fun deleteAPIKey(apiKey: APIKey): TaskDelete
}