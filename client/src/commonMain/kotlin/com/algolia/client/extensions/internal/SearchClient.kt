package com.algolia.client.extensions.internal

import com.algolia.client.api.SearchClient
import com.algolia.client.model.search.SearchParamsObject
import com.algolia.client.model.search.SecuredApiKeyRestrictions
import io.ktor.http.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Builds a restriction string based on provided [SecuredApiKeyRestrictions].
 */
internal fun SearchClient.buildRestrictionString(restriction: SecuredApiKeyRestrictions): String {
  return Parameters.build {
    restriction.searchParams?.let { searchParams ->
      val json = options.json.encodeToJsonElement(SearchParamsObject.serializer(), searchParams).jsonObject
      json.forEach { (key, element) ->
        when (element) {
          is JsonArray -> appendAll(key, element.jsonPrimitive.content.map { it.toString() })
          else -> append(key, element.jsonPrimitive.content)
        }
      }
    }
    restriction.restrictIndices?.let { append("restrictIndices", it.joinToString(";")) }
    restriction.restrictSources?.let { append("restrictSources", it) }
    restriction.userToken?.let { append("userToken", it) }
    restriction.validUntil?.let { append("validUntil", it.toString()) }
  }.formUrlEncode()
}
