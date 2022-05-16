@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.internal.requestOptionsBuilder
import com.algolia.search.endpoint.EndpointSettings
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.settings.NumericAttributeFilter
import com.algolia.search.model.settings.SearchableAttribute
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.settings.SettingsKey
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.Route
import com.algolia.search.serialize.internal.jsonArrayOrNull
import com.algolia.search.serialize.internal.merge
import com.algolia.search.serialize.internal.toJsonNoDefaults
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

internal class EndpointSettingsImpl(
    private val transport: Transport,
    override val indexName: IndexName,
) : EndpointSettings {

    override suspend fun getSettings(requestOptions: RequestOptions?): Settings {
        val path = indexName.toPath("/${Route.Settings}")
        val json = transport.request<JsonObject>(HttpMethod.Get, CallType.Read, path, requestOptions)
        // The following lines handle the old names of attributes, thus providing backward compatibility.
        val settings = JsonNonStrict.decodeFromJsonElement(Settings.serializer(), json)
        val attributesToIndex = json[Key.AttributesToIndex]?.jsonArrayOrNull?.let {
            Json.decodeFromJsonElement(ListSerializer(SearchableAttribute), it)
        }
        val numericAttributesToIndex = json[Key.NumericAttributesToIndex]?.jsonArrayOrNull?.let {
            Json.decodeFromJsonElement(ListSerializer(NumericAttributeFilter), it)
        }
        val replicas = json[Key.Slaves]?.jsonArrayOrNull?.let {
            Json.decodeFromJsonElement(ListSerializer(IndexName), it)
        }

        return settings.copy(
            searchableAttributes = settings.searchableAttributes ?: attributesToIndex,
            numericAttributesForFiltering = settings.numericAttributesForFiltering ?: numericAttributesToIndex,
            replicas = settings.replicas ?: replicas
        )
    }

    private suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?,
        indexName: IndexName,
    ): RevisionIndex {
        val resets = buildJsonObject { resetToDefault.forEach { put(it.raw, JsonNull) } }
        val body = settings.toJsonNoDefaults().merge(resets).toString()
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.ForwardToReplicas, forwardToReplicas)
        }

        return transport.request(HttpMethod.Put, CallType.Write, indexName.toPath("/${Route.Settings}"), options, body)
    }

    override suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?,
    ): RevisionIndex {
        return setSettings(settings, resetToDefault, forwardToReplicas, requestOptions, indexName)
    }
}

/**
 * Create an [EndpointSettings] instance.
 */
internal fun EndpointSettings(
    transport: Transport,
    indexName: IndexName,
): EndpointSettings = EndpointSettingsImpl(transport, indexName)
