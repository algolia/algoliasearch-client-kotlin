package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class APIKeys(
    val keys: List<Item>
) {

    @Serializable
    data class Item(
        val value: APIKey,
        val rights: List<ACL>,
        val createdAt: Long,
        val validity: Long,
        val description: String
    )
}