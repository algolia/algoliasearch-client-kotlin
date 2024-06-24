package com.algolia.client.extensions.internal

import com.algolia.client.api.SearchClient
import com.algolia.client.model.search.SearchParamsObject
import com.algolia.client.model.search.SecuredApiKeyRestrictions
import io.ktor.http.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.util.SortedMap
import java.util.TreeMap

/**
 * Builds a restriction string based on provided [SecuredApiKeyRestrictions].
 */
internal fun SearchClient.buildRestrictionString(restriction: SecuredApiKeyRestrictions): String {
  val sortedParams: SortedMap<String, String> = TreeMap()

  restriction.searchParams?.let { searchParams ->
    val json = options.json.encodeToJsonElement(SearchParamsObject.serializer(), searchParams).jsonObject
    json.forEach { (key, element) ->
      val value = when (element) {
        is JsonArray -> element.joinToString(",") { it.jsonPrimitive.content }
        else -> element.jsonPrimitive.content
      }
      sortedParams[key] = value
    }
  }

  restriction.restrictIndices?.let {
    sortedParams["restrictIndices"] = it.joinToString(",")
  }
  restriction.restrictSources?.let {
    sortedParams["restrictSources"] = it
  }
  restriction.userToken?.let {
    sortedParams["userToken"] = it
  }
  restriction.filters?.let {
    sortedParams["filters"] = it
  }
  restriction.validUntil?.let {
    sortedParams["validUntil"] = it.toString()
  }

  return sortedParams.entries.joinToString("&") { "${it.key}=${it.value.encodeURLParameter()}" }
}
