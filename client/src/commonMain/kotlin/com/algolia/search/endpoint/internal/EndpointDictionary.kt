package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointDictionary
import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.Dictionary
import com.algolia.search.model.dictionary.Dictionary.Compounds
import com.algolia.search.model.dictionary.Dictionary.Plurals
import com.algolia.search.model.dictionary.Dictionary.Stopwords
import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.model.dictionary.DictionaryEntry.Compound
import com.algolia.search.model.dictionary.DictionaryEntry.Plural
import com.algolia.search.model.dictionary.DictionaryEntry.Stopword
import com.algolia.search.model.dictionary.DictionarySettings
import com.algolia.search.model.internal.request.RequestDictionary
import com.algolia.search.model.response.ResponseDictionary
import com.algolia.search.model.response.ResponseSearchDictionaries
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.Route
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import com.algolia.search.util.internal.cast
import io.ktor.http.HttpMethod
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray

internal class EndpointDictionaryImpl(
    private val transport: Transport,
) : EndpointDictionary {

    override suspend fun <T : DictionaryEntry> saveDictionaryEntries(
        dictionary: Dictionary<T>,
        dictionaryEntries: List<T>,
        requestOptions: RequestOptions?,
    ): ResponseDictionary {
        return batch(dictionary, dictionaryEntries, false, requestOptions)
    }

    override suspend fun <T : DictionaryEntry> replaceDictionaryEntries(
        dictionary: Dictionary<T>,
        dictionaryEntries: List<T>,
        requestOptions: RequestOptions?,
    ): ResponseDictionary {
        return batch(dictionary, dictionaryEntries, true, requestOptions)
    }

    /**
     * Send a batch of dictionary entries.
     *
     * @param dictionary target dictionary.
     * @param dictionaryEntries list of a dictionary entries
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    private suspend fun <T : DictionaryEntry> batch(
        dictionary: Dictionary<T>,
        dictionaryEntries: List<T>,
        clearExistingDictionaryEntries: Boolean,
        requestOptions: RequestOptions?,
    ): ResponseDictionary {
        val path = dictionary.toPath(ENDPOINT_BATCH)
        val serializer = ListSerializer(dictionary.entrySerializer()).cast<KSerializer<List<T>>>()
        val entries = JsonNoDefaults.encodeToJsonElement(serializer, dictionaryEntries).jsonArray
        val request = RequestDictionary.Add(entries, clearExistingDictionaryEntries)
        val body = JsonNoDefaults.encodeToString(RequestDictionary.Add.serializer(), request)
        return transport.request(HttpMethod.Post, CallType.Write, path, requestOptions, body)
    }

    override suspend fun <T : DictionaryEntry> deleteDictionaryEntries(
        dictionary: Dictionary<T>,
        objectIDs: List<ObjectID>,
        requestOptions: RequestOptions?,
    ): ResponseDictionary {
        val path = dictionary.toPath(ENDPOINT_BATCH)
        val entries = buildJsonArray {
            objectIDs.forEach {
                add(
                    buildJsonObject { put(Key.ObjectID, JsonPrimitive(it.raw)) } // { "objectID": "myID1" }
                )
            }
        }
        val request = RequestDictionary.Delete(
            clearExistingDictionaryEntries = false,
            entries = entries
        )
        val body = JsonNoDefaults.encodeToString(RequestDictionary.Delete.serializer(), request)
        return transport.request(HttpMethod.Post, CallType.Write, path, requestOptions, body)
    }

    override suspend fun <T : DictionaryEntry> clearDictionaryEntries(
        dictionary: Dictionary<T>,
        requestOptions: RequestOptions?,
    ): ResponseDictionary {
        return replaceDictionaryEntries(dictionary, emptyList(), requestOptions)
    }

    override suspend fun <T : DictionaryEntry> searchDictionaryEntries(
        dictionary: Dictionary<T>,
        query: Query,
        requestOptions: RequestOptions?,
    ): ResponseSearchDictionaries<T> {
        val path = dictionary.toPath(ENDPOINT_SEARCH)
        val body = JsonNoDefaults.encodeToString(Query.serializer(), query)
        val json = transport.request<JsonObject>(HttpMethod.Post, CallType.Read, path, requestOptions, body)
        return JsonNonStrict.decodeFromJsonElement(
            deserializer = ResponseSearchDictionaries.serializer(dictionary.entrySerializer()),
            element = json
        ).cast()
    }

    override suspend fun setDictionarySettings(
        dictionarySettings: DictionarySettings,
        requestOptions: RequestOptions?,
    ): ResponseDictionary {
        val path = "${Route.Dictionaries}/$ENDPOINT_SETTINGS"
        val body = JsonNoDefaults.encodeToString(DictionarySettings.serializer(), dictionarySettings)
        return transport.request(HttpMethod.Put, CallType.Write, path, requestOptions, body)
    }

    override suspend fun getDictionarySettings(requestOptions: RequestOptions?): DictionarySettings {
        val path = "${Route.Dictionaries}/$ENDPOINT_SETTINGS"
        return transport.request(HttpMethod.Get, CallType.Read, path, requestOptions)
    }

    /**
     * Get Dictionary instance's serializer.
     */
    private fun Dictionary<*>.entrySerializer() = when (this) {
        Plurals -> Plural.serializer()
        Stopwords -> Stopword.serializer()
        Compounds -> Compound.serializer()
    }

    companion object {
        const val ENDPOINT_SEARCH = "/search"
        const val ENDPOINT_BATCH = "/batch"
        const val ENDPOINT_SETTINGS = "*/settings"
    }
}

/**
 * Create an [EndpointDictionary] instance.
 */
internal fun EndpointDictionary(
    transport: Transport,
): EndpointDictionary = EndpointDictionaryImpl(transport)
