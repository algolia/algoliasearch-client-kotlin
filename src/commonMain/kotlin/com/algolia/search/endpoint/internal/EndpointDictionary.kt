package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointDictionary
import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.Dictionary
import com.algolia.search.model.dictionary.Dictionary.Compounds
import com.algolia.search.model.dictionary.Dictionary.Plurals
import com.algolia.search.model.dictionary.Dictionary.Stopwords
import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.model.dictionary.DictionarySettings
import com.algolia.search.model.internal.request.RequestDictionary
import com.algolia.search.model.response.ResponseDictionary
import com.algolia.search.model.response.ResponseSearchDictionaries
import com.algolia.search.model.search.Query
import com.algolia.search.model.task.DictionaryTaskID
import com.algolia.search.model.task.TaskInfo
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.RouteDictionaries
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import com.algolia.search.util.internal.cast
import io.ktor.http.HttpMethod
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.JsonObject

internal class EndpointDictionaryImpl(
    private val transport: Transport,
) : EndpointDictionary {

    override suspend fun <T : Dictionary> saveDictionaryEntries(
        dictionary: T,
        dictionaryEntries: List<DictionaryEntry<T>>,
        clearExistingDictionaryEntries: Boolean,
        requestOptions: RequestOptions?,
    ): ResponseDictionary {
        val path = dictionary.toPath(ENDPOINT_BATCH)
        val request = RequestDictionary.Add(
            clearExistingDictionaryEntries = clearExistingDictionaryEntries,
            entries = dictionaryEntries
        )
        val serializer = RequestDictionary.Add.serializer(dictionary.serializer())
            .cast<KSerializer<RequestDictionary.Add<T>>>()
        val body = JsonNoDefaults.encodeToString(serializer, request)
        return transport.request(HttpMethod.Post, CallType.Write, path, requestOptions, body)
    }

    override suspend fun <T : Dictionary> replaceDictionaryEntries(
        dictionary: T,
        dictionaryEntries: List<DictionaryEntry<T>>,
        requestOptions: RequestOptions?,
    ): ResponseDictionary {
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
    ): ResponseDictionary {
        val path = dictionary.toPath(ENDPOINT_BATCH)
        val request = RequestDictionary.Delete(
            clearExistingDictionaryEntries = false,
            entries = objectIDs
        )
        val body = JsonNoDefaults.encodeToString(request)
        return transport.request(HttpMethod.Post, CallType.Write, path, requestOptions, body)
    }

    override suspend fun clearDictionaryEntries(
        dictionary: Dictionary,
        requestOptions: RequestOptions?,
    ): ResponseDictionary {
        return replaceDictionaryEntries(dictionary, emptyList(), requestOptions)
    }

    override suspend fun <T : Dictionary> searchDictionaryEntries(
        dictionary: T,
        query: Query,
        requestOptions: RequestOptions?,
    ): ResponseSearchDictionaries<T> {
        val path = dictionary.toPath(ENDPOINT_SEARCH)
        val body = JsonNoDefaults.encodeToString(query)
        val json = transport.request<JsonObject>(HttpMethod.Post, CallType.Read, path, requestOptions, body)
        return JsonNonStrict.decodeFromJsonElement(
            deserializer = ResponseSearchDictionaries.serializer(dictionary.serializer()),
            element = json
        ).cast()
    }

    override suspend fun setDictionarySettings(
        dictionarySettings: DictionarySettings,
        requestOptions: RequestOptions?,
    ): ResponseDictionary {
        val path = "$RouteDictionaries/$ENDPOINT_SETTINGS"
        val body = JsonNoDefaults.encodeToString(dictionarySettings)
        return transport.request(HttpMethod.Put, CallType.Write, path, requestOptions, body)
    }

    override suspend fun getDictionarySettings(requestOptions: RequestOptions?): DictionarySettings {
        val path = "$RouteDictionaries/$ENDPOINT_SETTINGS"
        return transport.request(HttpMethod.Get, CallType.Read, path, requestOptions)
    }

    override suspend fun waitTask(
        taskID: DictionaryTaskID,
        timeout: Long?,
        requestOptions: RequestOptions?,
    ): TaskStatus {
        suspend fun loop(): TaskStatus {
            while (true) {
                val taskStatus = getTask(taskID, requestOptions).status
                if (TaskStatus.Published == taskStatus) return taskStatus
                delay(1000L)
            }
        }
        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    override suspend fun ResponseDictionary.wait(timeout: Long?, requestOptions: RequestOptions?): TaskStatus {
        return waitTask(taskID, timeout, requestOptions)
    }

    override suspend fun getTask(taskID: DictionaryTaskID, requestOptions: RequestOptions?): TaskInfo {
        return transport.request(HttpMethod.Get, CallType.Read, "$ENDPOINT_TASK/$taskID", requestOptions)
    }

    /**
     * Get Dictionary instance's serializer.
     */
    private fun Dictionary.serializer() = when (this) {
        Plurals -> Plurals.serializer()
        Stopwords -> Stopwords.serializer()
        Compounds -> Compounds.serializer()
    }

    companion object {
        const val ENDPOINT_SEARCH = "/search"
        const val ENDPOINT_BATCH = "/batch"
        const val ENDPOINT_SETTINGS = "*/settings"
        const val ENDPOINT_TASK = "1/task"
    }
}

/**
 * Create an [EndpointDictionary] instance.
 */
internal fun EndpointDictionary(
    transport: Transport,
): EndpointDictionary = EndpointDictionaryImpl(transport)
