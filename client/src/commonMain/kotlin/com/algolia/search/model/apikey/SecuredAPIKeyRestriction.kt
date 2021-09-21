package com.algolia.search.model.apikey

import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.toJsonNoDefaults
import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonPrimitive

/**
 * Create restrictions for an [APIKey].
 *
 * @param query A mapping of search parameters that will be forced at query time.
 * @param restrictIndices List of [IndexName] that can be queried.
 * @param restrictSources IPv4 network allowed to use the generated key. This is used for more protection against
 * [APIKey] leaking and reuse.
 * @param validUntil A Unix timestamp used to define the expiration date of the [APIKey].
 * @param userToken Specify a user identifier. This is often used with rate limits. By default, rate limits will only
 * use the IP. This can be an issue when several of your end users are using the same IP. To avoid that, you can set a
 * userToken query parameter when generating the key. When set, a unique user will be identified by his IP + user_token
 * instead of only by his IP. This allows you to restrict a single user to performing a maximum of N API calls per hour,
 * even if he shares his IP with another user.
 */
public data class SecuredAPIKeyRestriction(
    val query: Query? = null,
    val restrictIndices: List<IndexName>? = null,
    val restrictSources: List<String>? = null,
    val validUntil: Long? = null,
    val userToken: UserToken? = null
) {

    @OptIn(InternalAPI::class) // https://youtrack.jetbrains.com/issue/KT-48127
    internal fun buildRestrictionString(): String {
        return Parameters.build {
            query?.let { query ->
                query.toJsonNoDefaults().forEach { (key, element) ->
                    when (element) {
                        is JsonArray -> appendAll(key, element.jsonPrimitive.content.map { it.toString() })
                        else -> append(key, element.jsonPrimitive.content)
                    }
                }
            }
            restrictIndices?.let { append(RESTRICT_INDICES, it.joinToString(";") { indexName -> indexName.raw }) }
            restrictSources?.let { append(RESTRICT_SOURCES, it.joinToString(";")) }
            userToken?.let { append(USER_TOKEN, it.raw) }
            validUntil?.let { append(VALID_UNTIL, it.toString()) }
        }.formUrlEncode()
    }

    public companion object {
        private const val RESTRICT_INDICES = "restrictIndices"
        private const val RESTRICT_SOURCES = "restrictSources"
        private const val USER_TOKEN = "userToken"
        private const val VALID_UNTIL = "validUntil"

        /**
         * Create restrictions for an [APIKey].
         */
        public operator fun invoke(
            query: Query? = null,
            restrictIndices: String? = null,
            restrictSources: String? = null,
            validUntil: Long? = null,
            userToken: UserToken? = null
        ): SecuredAPIKeyRestriction = SecuredAPIKeyRestriction(
            query = query,
            restrictIndices = restrictIndices?.split(";")?.map(::IndexName),
            restrictSources = restrictSources?.split(";"),
            validUntil = validUntil,
            userToken = userToken
        )
    }
}
