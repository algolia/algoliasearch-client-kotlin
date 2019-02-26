package com.algolia.search.model.apikey

import com.algolia.search.model.IndexName
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.toJsonNoDefaults
import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.content


data class SecuredAPIKeyRestriction(
    val query: Query? = null,
    val indexNames: List<IndexName>? = null,
    val sources: List<String>? = null,
    val validUntil: Long? = null,
    val userToken: UserToken? = null
) {

    internal fun buildRestrictionString(): String {
        return Parameters.build {
            query?.let { query ->
                query.toJsonNoDefaults().forEach { (key, element) ->
                    when (element) {
                        is JsonArray -> appendAll(key, element.content.map { it.content })
                        else -> append(key, element.content)
                    }
                }
            }
            indexNames?.let { append("restrictIndices", it.joinToString(";") { indexName -> indexName.raw }) }
            sources?.let { append("restrictSources", it.joinToString(";")) }
            userToken?.let { append("userToken", it.raw) }
            validUntil?.let { append("validUntil", it.toString()) }
        }.formUrlEncode()
    }
}