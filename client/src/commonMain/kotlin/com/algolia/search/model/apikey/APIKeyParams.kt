package com.algolia.search.model.apikey

import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query

/**
 * Parameters and functionality of an [APIKey].
 */
public data class APIKeyParams(
    /**
     * Set of permissions [ACL] associated to an [APIKey].
     */
    val ACLs: List<ACL>? = null,
    /**
     * A Unix timestamp used to define the expiration date of an [APIKey].
     */
    val validity: Long? = null,
    /**
     * Specify the maximum number of hits an [APIKey] can retrieve in one call.
     * This parameter can be used to protect you from attempts at retrieving your entire index contents by massively
     * querying the index.
     */
    val maxHitsPerQuery: Int? = null,
    /**
     * Specify the maximum number of API calls allowed from an IP address per hour.
     * Each time an API call is performed with an [APIKey], a check is performed.
     * If the IP at the source of the call did more than this number of calls in the last hour, a 429 code is returned.
     * This parameter can be used to protect you from attempts at retrieving your entire index contents by massively
     * querying the index.
     */
    val maxQueriesPerIPPerHour: Int? = null,
    /**
     * Specify the list of targeted indices. You can target all indices starting with a prefix or ending with a
     * suffix using the ‘*’ character.
     * For example, “dev_*” matches all indices starting with “dev_” and “*_dev” matches all indices ending with “_dev”.
     */
    val indices: List<IndexName>? = null,
    /**
     * Specify the list of referers. You can target all referers starting with a prefix, ending with a suffix using
     * the character `*`. For example, "https://algolia.com/`*`" matches all referers starting with
     * "https://algolia.com/" and "`*`.algolia.com" matches all referers ending with ".algolia.com".
     * If you want to allow the domain algolia.com you can use "`*`algolia.com/`*`".
     */
    val referers: List<String>? = null,
    /**
     * Specify the [Query] parameters. You can force the [Query] parameters for a [Query] using the url string format.
     * Example: “typoTolerance=strict&ignorePlurals=false”.
     */
    val query: Query? = null,
    /**
     * Specify a description of the [APIKey]. Used for informative purposes only.
     * It has impact on the functionality of the [APIKey].
     */
    val description: String? = null
)
