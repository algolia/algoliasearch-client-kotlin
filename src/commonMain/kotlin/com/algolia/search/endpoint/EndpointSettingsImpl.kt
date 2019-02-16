package com.algolia.search.endpoint

import com.algolia.search.client.*
import com.algolia.search.model.IndexName
import com.algolia.search.model.enums.NumericAttributeFilter
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.settings.SearchableAttribute
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.settings.SettingsKey
import com.algolia.search.serialize.*
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import kotlinx.serialization.list


internal class EndpointSettingsImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointSettings,
    APIWrapper by api {

    private val route = "/settings"

    override suspend fun getSettings(requestOptions: RequestOptions?): Settings {
        return retryRead(requestOptions, indexName.toPath(route)) { url ->
            val json = httpClient.get<JsonObject>(url) {
                setRequestOptions(requestOptions)
            }
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

            settings.copy(
                searchableAttributes = settings.searchableAttributes ?: attributesToIndex,
                numericAttributesForFiltering = settings.numericAttributesForFiltering ?: numericAttributesToIndex,
                replicas = settings.replicas ?: slaves
            )
        }
    }

    private suspend fun setSettings(
        settings: Settings,
        resetToDefault: List<SettingsKey>,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?,
        indexName: IndexName
    ): RevisionIndex {
        val resets = json { resetToDefault.forEach { it.raw to JsonNull } }
        val bodyString = settings.toJsonNoDefaults().merge(resets).toString()

        return retryWrite(requestOptions, indexName.toPath(route)) { url ->
            httpClient.put<RevisionIndex>(url) {
                body = bodyString
                parameter(KeyForwardToReplicas, forwardToReplicas)
                setRequestOptions(requestOptions)
            }
        }
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