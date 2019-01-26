package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions
import com.algolia.search.saas.model.*


interface EndpointSynonym {

    val indexName: IndexName

    suspend fun saveSynonym(
        synonym: Synonym,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdateSynonym

    suspend fun saveSynonyms(
        synonyms: List<Synonym>,
        forwardToReplicas: Boolean? = null,
        replaceExistingSynonyms: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdateIndex

    suspend fun getSynonym(objectID: ObjectID, requestOptions: RequestOptions? = null): Synonym

    suspend fun deleteSynonym(
        objectID: ObjectID,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskDelete

    suspend fun searchSynonyms(
        query: String? = null,
        page: Int? = null,
        hitsPerPage: Int? = null,
        synonymType: List<SynonymType>? = null,
        requestOptions: RequestOptions? = null
    ): SynonymHits

    suspend fun clearSynonyms(
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): TaskUpdateIndex
}