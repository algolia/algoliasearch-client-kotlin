package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointDictionary
import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.Dictionary
import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.model.dictionary.DictionarySettings
import com.algolia.search.model.internal.request.RequestAddDictionary
import com.algolia.search.model.internal.request.RequestDeleteDictionary
import com.algolia.search.model.response.ResponseSearchDictionaries
import com.algolia.search.model.response.deletion.DeletionIndex
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.Json
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.encodeToString

internal class EndpointDictionaryImpl(
    private val transport: Transport,
) : EndpointDictionary {

    override suspend fun <T : Dictionary> saveDictionaryEntries(
        dictionary: T,
        dictionaryEntries: List<DictionaryEntry<T>>,
        clearExistingDictionaryEntries: Boolean,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        val path = dictionary.toPath("/batch")
        val request = RequestAddDictionary(
            clearExistingDictionaryEntries = clearExistingDictionaryEntries,
            entries = dictionaryEntries
        )
        val body = Json.encodeToString(request)
        return transport.request(HttpMethod.Post, CallType.Write, path, requestOptions, body)
    }

    override suspend fun <T : Dictionary> replaceDictionaryEntries(
        dictionary: T,
        dictionaryEntries: List<DictionaryEntry<T>>,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        return saveDictionaryEntries(
            dictionary = dictionary,
            dictionaryEntries = dictionaryEntries,
            clearExistingDictionaryEntries = true,
            requestOptions = requestOptions
        )
    }

    override suspend fun deleteDictionaryEntries(
        dictionary: Dictionary,
        objectIDs: List<ObjectID>,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        val path = dictionary.toPath("/batch")
        val request = RequestDeleteDictionary(
            clearExistingDictionaryEntries = false,
            entries = objectIDs
        )
        val body = Json.encodeToString(request)
        return transport.request(HttpMethod.Post, CallType.Write, path, requestOptions, body)
    }

    override suspend fun clearDictionaryEntries(
        dictionary: Dictionary,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        return replaceDictionaryEntries(dictionary, emptyList(), requestOptions)
    }

    override suspend fun searchDictionaryEntries(
        dictionary: Dictionary,
        query: Query,
        requestOptions: RequestOptions?,
    ): ResponseSearchDictionaries {
        TODO("Not yet implemented")
    }

    override suspend fun setDictionarySettings(
        dictionarySettings: DictionarySettings,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        TODO("Not yet implemented")
    }

    override suspend fun getDictionarySettings(requestOptions: RequestOptions?): DictionarySettings {
        TODO("Not yet implemented")
    }
}

/**
 * Create an [EndpointDictionary] instance.
 */
internal fun EndpointDictionary(
    transport: Transport,
): EndpointDictionary = EndpointDictionaryImpl(transport)
