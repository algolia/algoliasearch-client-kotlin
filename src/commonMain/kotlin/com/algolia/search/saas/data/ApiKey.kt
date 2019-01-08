package com.algolia.search.saas.data


data class ApiKey(override val raw: String) : Raw<String> {

    override fun toString(): String {
        return raw
    }
}