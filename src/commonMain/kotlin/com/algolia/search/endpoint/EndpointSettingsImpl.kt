package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
import com.algolia.search.helper.requestOptionsBuilder
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.settings.NumericAttributeFilter
import com.algolia.search.model.settings.SearchableAttribute
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.settings.SettingsKey
import com.algolia.search.serialize.*
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import kotlinx.serialization.list


internal class EndpointSettingsImpl(
    private val transport: Transport,
    override val indexName: IndexName
) : EndpointSettings {

    override suspend fun getSettings(requestOptions: RequestOptions?): Settings {
        val path = indexName.toPath("/$RouteSettings")
        val json = transport.request<JsonObject>(HttpMethod.Get, CallType.Read, path, requestOptions)
        // The following lines handle the old names of attributes, thus providing backward compatibility.
        val settings = Json.nonstrict.fromJson(Settings.serializer(), json)
        val attributesToIndex = json.getArrayOrNull(KeyAttributesToIndex)?.let {
            Json.plain.fromJson(SearchableAttribute.list, it)
        }
        val numericAttributesToIndex = json.getArrayOrNull(KeyNumericAttributesToIndex)?.let {
            Json.plain.fromJson(NumericAttributeFilter.list, it)
        }
        val slaves = json.getArrayOrNull(KeySlaves)?.let {
            Json.plain.fromJson(IndexName.list, it)
        }

        return settings.copy(
            searchableAttributes = settings.searchableAttributes ?: attributesToIndex,
            numericAttributesForFiltering = settings.numericAttributesForFiltering ?: numericAttributesToIndex,
            replicas = settings.replicas ?: slaves
        )
    }

    private suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?,
        indexName: IndexName
    ): RevisionIndex {
        val resets = json { resetToDefault.forEach { it.raw to JsonNull } }
        val body = settings.toJsonNoDefaults().merge(resets).toString()
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyForwardToReplicas, forwardToReplicas)
        }

        return transport.request(HttpMethod.Put, CallType.Write, indexName.toPath("/$RouteSettings"), options, body)
    }

    override suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionIndex {
        return setSettings(settings, resetToDefault, forwardToReplicas, requestOptions, indexName)
    }
}