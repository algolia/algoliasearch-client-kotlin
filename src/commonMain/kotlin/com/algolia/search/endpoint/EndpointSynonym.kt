package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymResponse
import com.algolia.search.model.synonym.SynonymType


interface EndpointSynonym {

    val indexName: IndexName

    suspend fun saveSynonym(
        synonym: Synonym,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): SynonymResponse.UpdateObject

    suspend fun saveSynonyms(
        synonyms: List<Synonym>,
        forwardToReplicas: Boolean? = null,
        replaceExistingSynonyms: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): SynonymResponse.Update

    suspend fun getSynonym(objectID: ObjectID, requestOptions: RequestOptions? = null): Synonym

    suspend fun deleteSynonym(
        objectID: ObjectID,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): SynonymResponse.Delete

    suspend fun searchSynonyms(
        query: String? = null,
        page: Int? = null,
        hitsPerPage: Int? = null,
        synonymType: List<SynonymType>? = null,
        requestOptions: RequestOptions? = null
    ): SynonymResponse.Search

    suspend fun clearSynonyms(
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): SynonymResponse.Update
}