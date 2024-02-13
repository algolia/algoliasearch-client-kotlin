package com.algolia.client.extensions.internal

import com.algolia.client.api.SearchClient
import com.algolia.client.extensions.SecuredAPIKeyRestriction
import com.algolia.client.model.search.SearchParamsObject
import io.ktor.http.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Builds a restriction string based on provided [SecuredAPIKeyRestriction].
 */
internal fun SearchClient.buildRestrictionString(restriction: SecuredAPIKeyRestriction): String {
  return Parameters.build {
    restriction.query?.let { query ->
      val json = options.json.encodeToJsonElement(SearchParamsObject.serializer(), query).jsonObject
      json.forEach { (key, element) ->
        when (element) {
          is JsonArray -> appendAll(key, element.jsonPrimitive.content.map { it.toString() })
          else -> append(key, element.jsonPrimitive.content)
        }
      }
    }
    restriction.restrictIndices?.let { append("restrictIndices", it.joinToString(";")) }
    restriction.restrictSources?.let { append("restrictSources", it.joinToString(";")) }
    restriction.userToken?.let { append("userToken", it) }
    restriction.validUntil?.let { append("validUntil", it.toEpochMilliseconds().toString()) }
  }.formUrlEncode()
}
